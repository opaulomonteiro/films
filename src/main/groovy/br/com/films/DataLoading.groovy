package br.com.films

import br.com.films.data.Film
import br.com.films.repository.FilmsRepository
import groovy.transform.CompileStatic
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import org.springframework.core.io.ClassPathResource

@CompileStatic
class DataLoading {

    private static final String[] HEADERS = ["year", "title", "studios", "producers", "winner"]

    private FilmsRepository filmsRepository

    DataLoading(FilmsRepository filmsRepository) {
        this.filmsRepository = filmsRepository
    }

    void execute() {
        Reader reader = new FileReader(new ClassPathResource("/static/movielist.csv").getFile())
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(reader)

        List<Film> films = []

        records.collect { CSVRecord it ->
            films.add(
                    new Film(
                            year: it.get("year") as String,
                            title: it.get("title") as String,
                            studios: it.get("studios").split(",") as List,
                            producers: (it.get("producers").replace(",", " and ").split(" and ") as List).findAll { it != "" } as List,
                            winner: it.get("winner") as Boolean
                    )
            )
        }
        filmsRepository.batchSave(films)
    }
}