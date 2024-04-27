package com.bard.newssitedata.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return new Car(
//                rs.getLong("car_id"),
//                rs.getString("brand"),
//                rs.getString("model"),
//                rs.getString("color"),
//                rs.getInt("year")
//        );
        return null;
    }
}
