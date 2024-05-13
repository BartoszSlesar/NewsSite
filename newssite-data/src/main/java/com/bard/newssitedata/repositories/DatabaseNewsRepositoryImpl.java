package com.bard.newssitedata.repositories;

import com.bard.newssitedata.config.ResultsConfig;
import com.bard.newssitedata.model.Article;
import com.bard.newssitedata.model.ArticleRowMapper;
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
import java.util.List;


@Repository
@RequiredArgsConstructor
public class DatabaseNewsRepositoryImpl implements DatabaseNewsRepository {

    private final ArticleRowMapper articleRowMapper;

    private final JdbcTemplate jdbcTemplate;

    private final ResultsConfig resultsConfig;


    private final String INSERT_SQL = "INSERT INTO news (" +
            "news_source," +
            "author," +
            "title," +
            "description," +
            "source_url," +
            "image_url," +
            "published_at," +
            "content" +
            ") VALUES (?,?,?,?,?,?,?,?)";


    @Override
    public List<Article> getArticles(int page, int limit) {
        DateTimeFormatter currentDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = LocalDateTime.now().format(currentDateFormatter);
        if (page < 1) {
            page = 1;
        }
        if (limit < 1 || limit > resultsConfig.getLimit()) {
            limit = resultsConfig.getLimit();
        }
        int offset = (page - 1) * limit;

        String sql = "SELECT * FROM news WHERE published_at LIKE ? OFFSET ? LIMIT ?";
        return this.jdbcTemplate.query(sql, this.articleRowMapper, currentDate + "%", offset, limit);
    }

    @Override
    public void saveArticle(Article article) {
        updateDatabase(article);
    }

    @Override
    @Transactional
    public void saveArticle(List<Article> articles) {
        for (Article article : articles) {

            updateDatabase(article);


        }

    }

    private void updateDatabase(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            PreparedStatement ps = con
                    .prepareStatement(this.INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, article.getSource());
            ps.setString(2, article.getAuthor());
            ps.setString(3, article.getTitle());
            ps.setString(4, article.getDescription());
            ps.setString(5, article.getUrl());
            ps.setString(6, article.getUrlToImage());
            ps.setString(7, article.getPublishedAt());
            ps.setString(8, article.getContent());
            return ps;
        }, keyHolder);
        article.setArticleId((int) keyHolder.getKeys().get("article_id"));
    }

    @Override
    public void updateArticle(Article article) {

    }

    @Override
    public void deleteArticle(long id) {

    }
}
