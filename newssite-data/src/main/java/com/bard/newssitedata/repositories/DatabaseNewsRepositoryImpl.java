package com.bard.newssitedata.repositories;

import com.bard.newssitedata.config.ResultsConfig;
import com.bard.newssitedata.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class DatabaseNewsRepositoryImpl implements DatabaseNewsRepository {

    private final NewsRowMapper newsRowMapper;

    private final JdbcTemplate jdbcTemplate;

    private final ResultsConfig resultsConfig;


    private final String INSERT_SQL = "INSERT INTO news (" +
            "news_id," +
            "news_source," +
            "author," +
            "title," +
            "description," +
            "source_url," +
            "image_url," +
            "published_at," +
            "content" +
            ") SELECT ?,?,?,?,?,?,?,?,? WHERE NOT EXISTS (SELECT news_id FROM news WHERE news_id=?)";


    @Override
    public List<News> getNews(int page, int limit) {
        DateTimeFormatter currentDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String currentDate = LocalDateTime.now().format(currentDateFormatter);
        String currentDate = "2024-05-16";

        if (page < 1) {
            page = 1;
        }
        if (limit < 1 || limit > resultsConfig.getLimit()) {
            limit = resultsConfig.getLimit();
        }
        int offset = (page - 1) * limit;

        String sql = "SELECT * FROM news WHERE published_at LIKE ? OFFSET ? LIMIT ?";
        return this.jdbcTemplate.query(sql, this.newsRowMapper, currentDate + "%", offset, limit);


    }



    @Override
    @Transactional
    public List<News> saveNews(List<News> newsList) {
        List<News> removedDuplicated = new ArrayList<>();
        for (News news : newsList) {

            News tmpNews = updateDatabase(news);
            if (tmpNews.getArticleId() >= 0) {
                removedDuplicated.add(tmpNews);
            }

        }

        return removedDuplicated;

    }

    private News updateDatabase(News news) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            PreparedStatement ps = con
                    .prepareStatement(this.INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, news.getNewsId());
            ps.setString(2, news.getSource());
            ps.setString(3, news.getAuthor());
            ps.setString(4, news.getTitle());
            ps.setString(5, news.getDescription());
            ps.setString(6, news.getUrl());
            ps.setString(7, news.getUrlToImage());
            ps.setString(8, news.getPublishedAt());
            ps.setString(9, news.getContent());
            ps.setString(10, news.getNewsId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKeys() != null) {
            news.setArticleId((int) keyHolder.getKeys().get("article_id"));
        } else {
            news.setArticleId(-1);
        }

        return news;
    }

    @Override
    public void updateNews(News news) {

    }

    @Override
    public void deleteNews(long id) {

    }
}
