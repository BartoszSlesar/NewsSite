package com.bard.newssitedata.repositories;

import com.bard.newssitedata.config.NewsApiConfig;
import com.bard.newssitedata.model.Article;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;


@Repository
public class NewsApiRepositoryImpl implements NewsApiRepository {

    private final RestClient restClient;

    private final NewsApiConfig newsApiConfig;

    public NewsApiRepositoryImpl(NewsApiConfig newsApiConfig) {
        this.newsApiConfig = newsApiConfig;
        this.restClient = RestClient.builder()
                .baseUrl("https://newsapi.org/v2/everything").build();
    }


    @Override
    public List<Article> getCurrentNews(String topic, LocalDate from, LocalDate to) {

        return null;
    }
}
