package com.bard.newssitedata.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsRowMapper implements RowMapper<News> {
    @Override
    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new News(
                rs.getLong("article_id"),
                rs.getString("news_id"),
                rs.getString("news_source"),
                rs.getString("author"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("source_url"),
                rs.getString("image_url"),
                rs.getString("published_at"),
                rs.getString("content")

        );

    }
}
