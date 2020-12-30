package br.com.films

import br.com.films.data.ProducerAwardResponse
import br.com.films.data.ProducerIntervalAwards
import br.com.films.exception.FilmsNotFoundException
import br.com.films.repository.FilmEntity
import br.com.films.repository.FilmsRepository
import br.com.films.service.FilmsService
import spock.lang.Specification

class FilmsServiceTest extends Specification {

    FilmsRepository filmsRepository = Mock(FilmsRepository)
    FilmsService filmsService = new FilmsService(filmsRepository)


    def "should return producer interval awards correctly"() {
        given:
        filmsRepository.getFilms() >> mockFilms()

        when:
        ProducerIntervalAwards teste = filmsService.getProducerAwards()

        then:
        assert teste.min == new ProducerAwardResponse(producer: 'Bo Derek', interval: 6, previousWin: 1984, followingWin: 1990)
        assert teste.max == new ProducerAwardResponse(producer: 'Buzz Feitshans', interval: 9, previousWin: 1985, followingWin: 1994)
    }

    def "should throw FilmsNotFoundException when data base is empty"() {
        given:
        filmsRepository.getFilms() >> []

        when:
        filmsService.getProducerAwards()

        then:
        Exception ex = thrown(FilmsNotFoundException)
        ex.message == 'Films database is empty'
    }

    private List<FilmEntity> mockFilms() {
        [
                new FilmEntity(
                        id: 1,
                        year: '1984',
                        title: 'Bolero',
                        studios: ['Universal Pictures'],
                        producers: ['Bo Derek'],
                        winner: true
                ),
                new FilmEntity(
                        id: 2,
                        year: '1990',
                        title: 'Ghosts Can\'t Do It',
                        studios: ['Universal Pictures'],
                        producers: ['Bo Derek'],
                        winner: true
                ),
                new FilmEntity(
                        id: 3,
                        year: '1985',
                        title: 'Rambo: First Blood Part II',
                        studios: ['Universal Pictures'],
                        producers: ['Buzz Feitshans'],
                        winner: true
                ),
                new FilmEntity(
                        id: 4,
                        year: '1994',
                        title: 'Color of Night',
                        studios: ['Universal Pictures'],
                        producers: ['Buzz Feitshans'],
                        winner: true
                ),
                new FilmEntity(
                        id: 4,
                        year: '1994',
                        title: 'Robocop',
                        studios: ['Universal Pictures'],
                        producers: ['Gerald R. Molen'],
                        winner: false
                )
        ]
    }
}