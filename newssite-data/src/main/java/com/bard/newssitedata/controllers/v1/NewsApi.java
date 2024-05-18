package com.bard.newssitedata.controllers.v1;


import com.bard.newssitedata.config.ResultsConfig;
import com.bard.newssitedata.model.News;
import com.bard.newssitedata.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
@CrossOrigin("*")
public class NewsApi {

    private final NewsService newsService;

    private final ResultsConfig resultsConfig;


    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<News>> getNews() {
        List<News> news = this.newsService.getCurrentNews(1, resultsConfig.getLimit());
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping(params = {"page", "limit"}, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<News>> getNews(
            @RequestParam(name = "page", required = false) int page,
            @RequestParam(name = "limit", required = false) int limit) {
        List<News> news = this.newsService.getCurrentNews(page, limit);
//        TODO Naprawić to jutro, zwraca 500
        return new ResponseEntity<>(news, HttpStatus.OK);
    }
}
