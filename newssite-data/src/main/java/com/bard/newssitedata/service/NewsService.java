package com.bard.newssitedata.service;

import com.bard.newssitedata.config.ResultsConfig;
import com.bard.newssitedata.model.Article;
import com.bard.newssitedata.model.ArticlesPages;
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

    public ArticlesPages getCurrentNews() {
        return this.getCurrentNews(1, resultsConfig.getLimit());
    }

    public ArticlesPages getCurrentNews(int page, int limit) {
        ArticlesPages articles = this.databaseNewsRepository.getArticles(page, limit);
        if (articles.getResults().isEmpty()) {
            List<Article> articleList = this.newsApiRepository.getCurrentNews("technology", page, limit);
            articles.setResults(articleList);
            this.databaseNewsRepository.saveArticle(articleList);
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
