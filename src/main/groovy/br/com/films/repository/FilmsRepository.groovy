package br.com.films.repository

import br.com.films.data.Film
import groovy.util.logging.Slf4j
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Slf4j
class FilmsRepository {

    private NamedParameterJdbcTemplate jdbcTemplate
    private FilmRowMapper rowMapper

    FilmsRepository(NamedParameterJdbcTemplate jdbcTemplate, FilmRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate
        this.rowMapper = rowMapper
    }

    List<FilmEntity> getFilms() {
        String sql = """SELECT id,year,title,studios,producers,winner FROM films"""
        try {
            return jdbcTemplate.query(sql, rowMapper) as List<FilmEntity>
        } catch (DataAccessException exception) {
            log.error("Error trying to get list of films", exception)
            return []
        }
    }

    int[] batchSave(List<Film> films) {
        MapSqlParameterSource[] mapSqlParameterSource = films.collect { film ->
            MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            parameterSource.addValue('year', film.year)
            parameterSource.addValue('title', film.title)
            parameterSource.addValue('studios', film.studios.toArray())
            parameterSource.addValue('producers', film.producers.toArray())
            parameterSource.addValue('winner', film.winner)
            return parameterSource
        } as MapSqlParameterSource[]

        String query = "INSERT INTO FILMS (YEAR,TITLE,STUDIOS,PRODUCERS,WINNER) " +
                "VALUES(:year,:title,:studios,:producers,:winner)"

        jdbcTemplate.batchUpdate(query, mapSqlParameterSource)
    }
}