package com.bard.newssitedata.controllers.v1;


import com.bard.newssitedata.config.ResultsConfig;
import com.bard.newssitedata.model.Article;
import com.bard.newssitedata.model.ArticlesPages;
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
    public ResponseEntity<ArticlesPages> getNews() {
        ArticlesPages articles = this.newsService.getCurrentNews(1, resultsConfig.getLimit());
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping(params = {"page", "limit"}, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ArticlesPages> getNews(
            @RequestParam(name = "page", required = false) int page,
            @RequestParam(name = "limit", required = false) int limit) {
        ArticlesPages articles = this.newsService.getCurrentNews(page, limit);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}
