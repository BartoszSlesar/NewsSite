package com.bard.newssitedata.repositories;

import com.bard.newssitedata.config.NewsApiConfig;
import com.bard.newssitedata.model.Article;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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
        DateTimeFormatter currentDateFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        String currentDate = LocalDateTime.now().format(currentDateFormatter);
        String endpoint = this.newsApiConfig.getNewsEndpoint()
                + "?q="
                + topic
                + "&from=" + currentDate
                + "&to=" + currentDate
                + "&apiKey=" + this.newsApiConfig.getApiKey();

//        TODO Tylko zwrocic article z uzyciem JsonNODE
//        ResponseEntity<String> response =
//                this.restClient.get().uri(endpoint).retrieve()
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            return List.of(response.getBody());
//        }
        return new ArrayList<>();
    }
}
