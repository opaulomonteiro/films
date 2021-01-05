package br.com.films.service;

import br.com.films.data.ProducerAward;
import br.com.films.data.ProducerAwardResponse;
import br.com.films.data.ProducerIntervalAwards;
import br.com.films.exception.FilmsNotFoundException;
import br.com.films.repository.FilmEntity;
import br.com.films.repository.FilmsRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.groupingBy;

public class FilmsService {

    private final FilmsRepository repository;

    public FilmsService(FilmsRepository repository) {
        this.repository = repository;
    }

    public ProducerIntervalAwards getProducerAwards() {
        List<FilmEntity> filmEntities = repository.getFilms();
        if (filmEntities == null || filmEntities.isEmpty()) throw new FilmsNotFoundException("Films database is empty");
        List<ProducerAward> producerAwards = getListOfProducersFromFilms(filmEntities);
        Map<String, List<ProducerAward>> producersWithAwards = producerAwards
                .stream()
                .sorted(Comparator.comparing(ProducerAward::getYear))
                .collect(groupingBy(ProducerAward::getName));
        return getProducersIntervalAwards(producersWithAwards);
    }

    private List<ProducerAward> getListOfProducersFromFilms(List<FilmEntity> filmEntities) {
        List<FilmEntity> winningFilms = filmEntities
                .stream()
                .filter(FilmEntity::isWinner)
                .collect(Collectors.toList());

        List<ProducerAward> producerAwards = new ArrayList<>();

        winningFilms.forEach(filmEntity ->
                filmEntity.getProducers().forEach(producerName ->
                        producerAwards.add(new ProducerAward(producerName.trim(), parseInt(filmEntity.getYear()), filmEntity.getTitle())
                        )));
        return producerAwards;
    }

    private ProducerIntervalAwards getProducersIntervalAwards(Map<String, List<ProducerAward>> producersWithAwards) {
        List<ProducerAwardResponse> awardResponses = producersWithAwards.values()
                .stream()
                .filter(producerAwards -> producerAwards.size() > 1)
                .map(this::getProducerAwardResponse)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        ProducerAwardResponse minProducerInterval = awardResponses.stream().min(Comparator.comparing(ProducerAwardResponse::getInterval)).get();
        ProducerAwardResponse maxProducerInterval = awardResponses.stream().max(Comparator.comparing(ProducerAwardResponse::getInterval)).get();

        List<ProducerAwardResponse> minIntervalAwards = awardResponses
                .stream()
                .filter(it -> it.getInterval().equals(minProducerInterval.getInterval()))
                .collect(Collectors.toList());


        List<ProducerAwardResponse> maxIntervalAwards = awardResponses
                .stream()
                .filter(it -> it.getInterval().equals(maxProducerInterval.getInterval()))
                .collect(Collectors.toList());

        return new ProducerIntervalAwards(minIntervalAwards, maxIntervalAwards);
    }

    private List<ProducerAwardResponse> getProducerAwardResponse(List<ProducerAward> producerAwards) {
        List<ProducerAwardResponse> awardResponses = new ArrayList<>();
        int iterator = 0;
        int interval = 0;
        int previousWin = 0;
        int followingWin = 0;
        for (ProducerAward producerAward : producerAwards) {
            if (previousWin == 0) previousWin = producerAward.getYear();
            if (followingWin == 0) followingWin = producerAward.getYear();
            if (interval == 0) interval = producerAward.getYear() - previousWin;
            if (producerAward.getYear() > previousWin) {
                followingWin = producerAward.getYear();
                interval = producerAward.getYear() - previousWin;
            }
            if (interval != 0) {
                awardResponses.add(
                        new ProducerAwardResponse(
                                producerAwards.stream().findFirst().get().getName(),
                                interval,
                                previousWin,
                                followingWin
                        ));
            }
            if (iterator < (producerAwards.size() - 1)) previousWin = producerAward.getYear();
            iterator++;
        }
        return awardResponses;
    }
}