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
    public List<Article> getCurrentNews(String topic, int page, int limit) {
        if (page < 1) {
            page = 1;
        }
        if (limit > resultsConfig.getLimit() || limit < 1) {
            limit = resultsConfig.getLimit();
        }
        DateTimeFormatter currentDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String currentDate = LocalDateTime.now().format(currentDateFormatter);
        String currentDate = "2024-05-16";
        String endpoint = this.newsApiConfig.getNewsEndpoint()
                + "?q="
                + topic
                + "&from=" + currentDate
                + "&to=" + currentDate
                + "&to=" + currentDate
                + "&page=" + page
                + "&pageSize=" + limit
                + "&apiKey=" + this.newsApiConfig.getApiKey();

        String rawResponse = this.restClient.get().uri(endpoint).retrieve().body(String.class);
//        ArticlesPages response = this.restClient.get().uri(endpoint).retrieve().body(ArticlesPages.class);

        List<Article> articles = new ArrayList<>();
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            JsonNode rawArticles = jsonObjectMapper.readTree(rawResponse).get("articles");
            for (JsonNode objNode : rawArticles) {
                articles.add(jsonObjectMapper.treeToValue(objNode, Article.class));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }


}
