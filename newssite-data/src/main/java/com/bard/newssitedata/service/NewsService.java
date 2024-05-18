package com.bard.newssitedata.service;

import com.bard.newssitedata.config.ResultsConfig;
import com.bard.newssitedata.model.Article;
import com.bard.newssitedata.model.News;
import com.bard.newssitedata.repositories.DatabaseNewsRepository;
import com.bard.newssitedata.repositories.NewsApiRepository;
import com.bard.newssitedata.utils.ArticleNewsConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NewsService {
    private final DatabaseNewsRepository databaseNewsRepository;
    private final NewsApiRepository newsApiRepository;
    private final ResultsConfig resultsConfig;
    private final ArticleNewsConverter articleNewsConverter;

    public List<News> getCurrentNews() {
        return this.getCurrentNews(1, resultsConfig.getLimit(), "");
    }

    public List<News> getCurrentNews(int page, int limit, String date) {
        List<News> news = this.databaseNewsRepository.getNews(page, limit, date);
        if (news.isEmpty() || (news.size() < limit && page > 1)) {
            List<Article> articleList = this.newsApiRepository.getCurrentNews("technology", page, limit, date);
            List<News> convertedArticles = this.articleNewsConverter.convertArticles(articleList);
            convertedArticles = this.databaseNewsRepository.saveNews(convertedArticles);
            news.addAll(convertedArticles);

        }
        return news;
    }

    public boolean updateArticle(News news) {
        return this.databaseNewsRepository.updateNews(news);
    }

    public boolean removeArticle(long id) {
        return this.databaseNewsRepository.deleteNews(id);
    }
}
