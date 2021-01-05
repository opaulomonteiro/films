package br.com.films.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class FilmRowMapper implements RowMapper<FilmEntity> {

    public FilmRowMapper() {
    }

    @Override
    public FilmEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        List<String> studios = Collections.singletonList((String) resultSet.getArray("studios").getArray());
        List<String> producers = Collections.singletonList((String) resultSet.getArray("producers").getArray());
        return new FilmEntity(
                resultSet.getInt("id"),
                resultSet.getString("year"),
                resultSet.getString("title"),
                studios,
                producers,
                resultSet.getBoolean("winner")
        );
    }
}