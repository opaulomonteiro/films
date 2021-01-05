package br.com.films.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmRowMapper implements RowMapper<FilmEntity> {

    public FilmRowMapper() {
    }

    @Override
    public FilmEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new FilmEntity(
                resultSet.getInt("id"),
                resultSet.getString("year"),
                resultSet.getString("title"),
                getColumnsAsList(resultSet, "studios"),
                getColumnsAsList(resultSet, "producers"),
                resultSet.getBoolean("winner")
        );
    }

    private List<String> getColumnsAsList(ResultSet resultSet, String columnLabel) throws SQLException {
        Array array = resultSet.getArray(columnLabel);
        List<String> values = new ArrayList<>();
        if (array != null) {
            ResultSet rs = array.getResultSet();
            while (rs.next()) {
                Object arrayElement = rs.getObject(2);
                String elementAsString = String.valueOf(arrayElement);
                values.add(elementAsString);
            }
        }
        return values;
    }
}