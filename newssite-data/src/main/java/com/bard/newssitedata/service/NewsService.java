package com.bard.newssitedata.service;

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

    public List<Article> getCurrentNews() {
        List<Article> articles = this.databaseNewsRepository.getArticles();
        if (articles.isEmpty()) {
            articles = this.newsApiRepository.getCurrentNews("technology");
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
