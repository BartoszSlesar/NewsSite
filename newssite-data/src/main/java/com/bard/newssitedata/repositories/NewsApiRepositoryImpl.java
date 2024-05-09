package com.bard.newssitedata.repositories;

import com.bard.newssitedata.config.NewsApiConfig;
import com.bard.newssitedata.model.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;


@Repository
public class NewsApiRepositoryImpl implements NewsApiRepository {

    private final RestClient restClient;

    private final NewsApiConfig newsApiConfig;


    public NewsApiRepositoryImpl(NewsApiConfig newsApiConfig) {
        this.newsApiConfig = newsApiConfig;
        this.restClient = RestClient.create();
    }


    @Override
    public List<Article> getCurrentNews(String topic) {
        DateTimeFormatter currentDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = LocalDateTime.now().format(currentDateFormatter);
        String endpoint = this.newsApiConfig.getNewsEndpoint()
                + "?q="
                + topic
                + "&from=" + "2024-05-08"
                + "&to=" + "2024-05-08"
                + "&pageSize=" + "50"
                + "&apiKey=" + this.newsApiConfig.getApiKey();

        String rawResponse = this.restClient.get().uri(endpoint).retrieve().body(String.class);

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
