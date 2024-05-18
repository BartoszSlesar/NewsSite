package com.bard.newssitedata.repositories;


import com.bard.newssitedata.config.NewsApiConfig;
import com.bard.newssitedata.config.ResultsConfig;
import com.bard.newssitedata.model.Article;
import com.bard.newssitedata.model.ArticlesPages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Repository
public class NewsApiRepositoryImpl implements NewsApiRepository {

    private final RestClient restClient;

    private final NewsApiConfig newsApiConfig;

    private final ResultsConfig resultsConfig;


    public NewsApiRepositoryImpl(NewsApiConfig newsApiConfig, ResultsConfig resultsConfig) {
        this.newsApiConfig = newsApiConfig;
        this.resultsConfig = resultsConfig;
        this.restClient = RestClient.create();
    }


    @Override
    public List<Article> getCurrentNews(String topic, int page, int limit, String date) {
        if (page < 1) {
            page = 1;
        }
        if (limit > resultsConfig.getLimit() || limit < 1) {
            limit = resultsConfig.getLimit();
        }
        if (date == null || date.isEmpty()) {
            DateTimeFormatter currentDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDateTime.now().format(currentDateFormatter);

        }
        String endpoint = this.newsApiConfig.getNewsEndpoint()
                + "?q="
                + topic
                + "&from=" + date
                + "&to=" + date
                + "&page=" + page
                + "&pageSize=" + limit
                + "&apiKey=" + this.newsApiConfig.getApiKey();


        ArticlesPages response = this.restClient.get().uri(endpoint).retrieve().body(ArticlesPages.class);

        return response == null ? new ArrayList<>() : response.getResults();
    }


}
