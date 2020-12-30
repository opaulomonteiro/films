package br.com.films.repository


import org.springframework.jdbc.core.RowMapper

import java.sql.ResultSet
import java.sql.SQLException

class FilmRowMapper implements RowMapper<FilmEntity> {

    FilmRowMapper() {}

    @Override
    FilmEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new FilmEntity(
                id: resultSet.getInt("id"),
                year: resultSet.getString("year"),
                title: resultSet.getString("title"),
                studios: resultSet.getArray("studios").getArray() as List,
                producers: resultSet.getArray("producers").getArray() as List,
                winner: resultSet.getBoolean("winner")
        )
    }
}