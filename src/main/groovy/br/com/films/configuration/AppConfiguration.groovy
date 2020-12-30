package br.com.films.configuration

import br.com.films.DataLoading
import br.com.films.repository.FilmRowMapper
import br.com.films.repository.FilmsRepository
import br.com.films.service.FilmsService
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class AppConfiguration {

    @Bean
    private DataLoading dataLoading(FilmsRepository filmsRepository) {
        new DataLoading(filmsRepository)
    }

    @Bean
    FilmsRepository filmsRepository(NamedParameterJdbcTemplate jdbcTemplate, FilmRowMapper filmRowMapper) {
        new FilmsRepository(jdbcTemplate, filmRowMapper)
    }

    @Bean
    FilmRowMapper filmRowMapper() {
        new FilmRowMapper()
    }

    @Bean
    FilmsService filmsService(FilmsRepository filmsRepository) {
        new FilmsService(filmsRepository)
    }
}