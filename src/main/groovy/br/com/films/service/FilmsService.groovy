package br.com.films.service

import br.com.films.data.ProducerAward
import br.com.films.data.ProducerAwardResponse
import br.com.films.data.ProducerIntervalAwards
import br.com.films.exception.FilmsNotFoundException
import br.com.films.repository.FilmEntity
import br.com.films.repository.FilmsRepository

class FilmsService {

    private FilmsRepository repository

    FilmsService(FilmsRepository repository) {
        this.repository = repository
    }

    ProducerIntervalAwards getProducerAwards() {
        List<FilmEntity> filmEntities = repository.getFilms()
        if (!filmEntities || filmEntities.isEmpty()) throw new FilmsNotFoundException("Films database is empty")
        List<ProducerAward> producerAwards = getListOfProducersFromFilms(filmEntities)
        Map<String, List<ProducerAward>> producersWithAwards = producerAwards
                .sort { ProducerAward it -> it.year }
                .groupBy { ProducerAward it -> it.name }
        return getProducersIntervalAwards(producersWithAwards)
    }

    private List<ProducerAward> getListOfProducersFromFilms(List<FilmEntity> filmEntities) {
        List<ProducerAward> producerAwards = []
        filmEntities.collect { FilmEntity filmEntity ->
            if (filmEntity.winner) {
                filmEntity.producers.collect { String producerName ->
                    producerAwards.add(
                            new ProducerAward(
                                    name: producerName.trim(),
                                    year: filmEntity.year as Integer,
                                    filmTitle: filmEntity.title))
                }
            }
        }
        return producerAwards
    }

    private ProducerIntervalAwards getProducersIntervalAwards(Map<String, List<ProducerAward>> producersWithAwards) {
        List<ProducerAwardResponse> awardResponses = []
        producersWithAwards
                .findAll { it.value.size() > 1 }
                .each {
                    List<ProducerAward> awards = it.value
                    Integer interval
                    Integer previousWin
                    Integer followingWin
                    awards.collect {
                        if (!previousWin) previousWin = it.year
                        if (!followingWin) followingWin = it.year
                        if (!interval) interval = it.year - previousWin
                        if (it.year > previousWin) {
                            followingWin = it.year
                            interval = it.year - previousWin
                        }
                    }
                    ProducerAwardResponse response = new ProducerAwardResponse(
                            producer: awards.name.first(),
                            interval: interval,
                            previousWin: previousWin,
                            followingWin: followingWin
                    )
                    awardResponses.add(response)
                }

        return new ProducerIntervalAwards(
                min: awardResponses.min { it.interval },
                max: awardResponses.max { it.interval }
        )
    }
}