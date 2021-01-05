package br.com.films;

import br.com.films.repository.FilmEntity;
import br.com.films.repository.FilmsRepository;
import br.com.films.service.FilmsService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Ignore
public class FilmsServiceTest {

    private FilmsRepository filmsRepository;
    private FilmsService filmsService;

    @BeforeEach
    public void setup() {
        this.filmsRepository = Mockito.mock(FilmsRepository.class);
        this.filmsService = new FilmsService(filmsRepository);
    }


    @Test
    public void shouldReturnProducerIntervalAwardsCorrectly() {
//        when(filmsRepository.getFilms()).thenReturn(mockFilms());
//
//        ProducerIntervalAwards teste = filmsService.getProducerAwards();
//
//        assertThat(teste.getMin(), new ProducerAwardResponse("Bo Derek", 6, 1984, 1990));
//
//        (new ProducerAwardResponse("Buzz Feitshans", 8, 1994, 2002),
//                teste.getMax()
//        );
    }

//    def "should throw FilmsNotFoundException when data base is empty"()
//
//    {
//        given:
//        filmsRepository.getFilms() >> []
//
//        when:
//        filmsService.getProducerAwards()
//
//        then:
//        Exception ex = thrown(FilmsNotFoundException)
//        ex.message == 'Films database is empty'
//    }

    private List<FilmEntity> mockFilms() {
        return Arrays.asList(
                new FilmEntity(
                        1,
                        "1984",
                        "Bolero",
                        Collections.singletonList("Universal Pictures"),
                        Collections.singletonList("Bo Derek"),
                        true
                ),
                new FilmEntity(
                        2,
                        "1990",
                        "Ghosts Can't Do It",
                        Collections.singletonList("Universal Pictures"),
                        Collections.singletonList("Bo Derek"),
                        true
                ),
                new FilmEntity(
                        3,
                        "1985",
                        "Rambo: First Blood Part II",
                        Collections.singletonList("Universal Pictures"),
                        Collections.singletonList("Buzz Feitshans"),
                        true
                ),
                new FilmEntity(
                        4,
                        "1994",
                        "Color of Night",
                        Collections.singletonList("Universal Pictures"),
                        Collections.singletonList("Buzz Feitshans"),
                        true
                ),
                new FilmEntity(
                        5,
                        "2002",
                        "Color of Night 2",
                        Collections.singletonList("Universal Pictures"),
                        Collections.singletonList("Buzz Feitshans"),
                        true
                ),
                new FilmEntity(
                        4,
                        "1994",
                        "Robocop",
                        Collections.singletonList("Universal Pictures"),
                        Collections.singletonList("Gerald R. Molen"),
                        false
                )
        );
    }
}
