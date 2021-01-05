package br.com.films.repository;

import br.com.films.data.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;
import java.util.List;

public class FilmsRepository {

    private final Logger logger = LoggerFactory.getLogger(FilmsRepository.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final FilmRowMapper rowMapper;

    public FilmsRepository(NamedParameterJdbcTemplate jdbcTemplate, FilmRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public List<FilmEntity> getFilms() {
        String sql = "SELECT id,year,title,studios,producers,winner FROM films";
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException exception) {
            logger.error("Error trying to get list of films", exception);
            return Collections.emptyList();
        }
    }

    public int[] batchSave(List<Film> films) {
        MapSqlParameterSource[] mapSqlParameterSource = films.stream().map(film -> {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("year", film.getYear());
            parameterSource.addValue("title", film.getTitle());
            parameterSource.addValue("studios", film.getStudios().toArray());
            parameterSource.addValue("producers", film.getProducers().toArray());
            parameterSource.addValue("winner", film.isWinner());
            return parameterSource;
        }).toArray(MapSqlParameterSource[]::new);

        String query = "INSERT INTO FILMS (YEAR,TITLE,STUDIOS,PRODUCERS,WINNER) " +
                "VALUES(:year,:title,:studios,:producers,:winner)";

        return jdbcTemplate.batchUpdate(query, mapSqlParameterSource);
    }
}