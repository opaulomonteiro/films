package br.com.films;

import br.com.films.data.ProducerAwardResponse;
import br.com.films.data.ProducerIntervalAwards;
import br.com.films.exception.FilmsNotFoundException;
import br.com.films.repository.FilmEntity;
import br.com.films.repository.FilmsRepository;
import br.com.films.service.FilmsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
        when(filmsRepository.getFilms()).thenReturn(mockFilms());

        ProducerIntervalAwards producerAwards = filmsService.getProducerAwards();

        assertEquals(
                producerAwards.getMin(),
                Arrays.asList(
                        new ProducerAwardResponse("Gerald R. Molen", 6, 1984, 1990),
                        new ProducerAwardResponse("Bo Derek", 6, 1984, 1990)
                )
        );

        assertEquals(
                producerAwards.getMax(),
                Collections.singletonList(new ProducerAwardResponse("Buzz Feitshans", 8, 1994, 2002))
        );
    }

    @Test
    public void shouldThrowFilmsNotFoundExceptionWhenDataBaseIsEmpty() {
        when(filmsRepository.getFilms()).thenReturn(Collections.emptyList());

        Assertions.assertThrows(FilmsNotFoundException.class, () -> filmsService.getProducerAwards());
    }

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
                        6,
                        "1984",
                        "Robocop",
                        Collections.singletonList("Universal Pictures"),
                        Collections.singletonList("Gerald R. Molen"),
                        true
                ),
                new FilmEntity(
                        7,
                        "1990",
                        "Filmaker",
                        Collections.singletonList("Universal Pictures"),
                        Collections.singletonList("Gerald R. Molen"),
                        true
                ),
                new FilmEntity(
                        8,
                        "1990",
                        "Fail Movie",
                        Collections.singletonList("Universal Pictures"),
                        Collections.singletonList("Gerald R. Molen"),
                        false
                )
        );
    }
}
