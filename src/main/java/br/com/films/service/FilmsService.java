package br.com.films.service;

import br.com.films.data.ProducerAward;
import br.com.films.data.ProducerAwardResponse;
import br.com.films.data.ProducerIntervalAwards;
import br.com.films.exception.FilmsNotFoundException;
import br.com.films.repository.FilmEntity;
import br.com.films.repository.FilmsRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
        return filmEntities
                .stream()
                .filter(FilmEntity::isWinner)
                .map(filmEntity -> {
                    String producerName = filmEntity.getProducers().stream().findFirst().orElse("DEFAULT_AUTHOR");
                    return new ProducerAward(
                            producerName.trim(),
                            parseInt(filmEntity.getYear()),
                            filmEntity.getTitle());
                }).collect(Collectors.toList());
    }

    private ProducerIntervalAwards getProducersIntervalAwards(Map<String, List<ProducerAward>> producersWithAwards) {
        List<ProducerAwardResponse> awardResponses = producersWithAwards.values()
                .stream()
                .filter(producerAwards -> producerAwards.size() > 1)
                .map(producerAwards -> {
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
                        if (iterator < (producerAwards.size() - 1)) previousWin = producerAward.getYear();
                        iterator++;
                    }
                    return new ProducerAwardResponse(
                            producerAwards.stream().findFirst().get().getName(),
                            interval,
                            previousWin,
                            followingWin
                    );
                }).collect(Collectors.toList());

        return new ProducerIntervalAwards(
                awardResponses.stream().min(Comparator.comparing(ProducerAwardResponse::getInterval)).get(),
                awardResponses.stream().max(Comparator.comparing(ProducerAwardResponse::getInterval)).get()
        );
    }
}