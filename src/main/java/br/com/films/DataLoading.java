package br.com.films;

import br.com.films.data.Film;
import br.com.films.repository.FilmsRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Boolean.parseBoolean;

public class DataLoading {

    private static final String[] HEADERS = new String[]{"year", "title", "studios", "producers", "winner"};

    private final FilmsRepository filmsRepository;

    public DataLoading(FilmsRepository filmsRepository) {
        this.filmsRepository = filmsRepository;
    }

    void execute() {
        Reader reader;
        try {
            reader = new FileReader(new ClassPathResource("/static/movielist.csv").getFile());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader()
                    .parse(reader);

            List<Film> films = StreamSupport.stream(records.spliterator(), false).map(it -> {
                Film film = new Film();
                film.setYear(it.get("year"));
                film.setTitle(it.get("title"));
                film.setStudios(Arrays.asList(it.get("studios").split(",")));
                film.setProducers(getProducers(it));
                film.setWinner(parseBoolean(it.get("winner")));
                return film;
            }).collect(Collectors.toList());

            filmsRepository.batchSave(films);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getProducers(CSVRecord record) {
        return Arrays.stream(record.get("producers")
                .replace(",", " and ").split(" and "))
                .filter(it -> !it.equals(""))
                .collect(Collectors.toList());
    }
}