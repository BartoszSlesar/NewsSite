package com.bard.newssitedata.service;

import com.bard.newssitedata.config.ResultsConfig;
import com.bard.newssitedata.model.Article;
import com.bard.newssitedata.repositories.DatabaseNewsRepository;
import com.bard.newssitedata.repositories.NewsApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NewsService {
    private final DatabaseNewsRepository databaseNewsRepository;
    private final NewsApiRepository newsApiRepository;
    private final ResultsConfig resultsConfig;

    public List<Article> getCurrentNews() {
        return this.getCurrentNews(1, resultsConfig.getLimit());
    }

    public List<Article> getCurrentNews(int page, int limit) {
        List<Article> articles = this.databaseNewsRepository.getArticles(page, limit);
        if (articles.isEmpty()) {
            articles = this.newsApiRepository.getCurrentNews("technology", page, limit);
            this.databaseNewsRepository.saveArticle(articles);
        }
        return articles;
    }

    public boolean updateArticle(Article article) {
        return false;
    }

    public boolean removeArticle(long id) {
        return false;
    }
}
