package com.bard.newssitedata.repositories;

import com.bard.newssitedata.model.Article;
import com.bard.newssitedata.model.ArticleRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class DatabaseNewsRepositoryImpl implements DatabaseNewsRepository {

    private final ArticleRowMapper articleRowMapper;

    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<Article> getArticles() {
        return null;
    }

    @Override
    public void saveArticle(Article article) {

    }

    @Override
    public void saveArticle(List<Article> articles) {

    }

    @Override
    public void updateArticle(Article article) {

    }

    @Override
    public void deleteArticle(long id) {

    }
}
