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
        return this.newsApiRepository.getCurrentNews("technology");
    }

    public boolean updateArticle(Article article) {
        return false;
    }

    public boolean removeArticle(long id) {
        return false;
    }
}
