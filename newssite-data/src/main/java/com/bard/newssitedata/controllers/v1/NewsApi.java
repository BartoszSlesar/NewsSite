package com.bard.newssitedata.controllers.v1;


import com.bard.newssitedata.model.Article;
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


    @GetMapping(params = {"page", "limit"}, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Article>> getCars(
            @RequestParam(name = "page", required = false) int page,
            @RequestParam(name = "limit", required = false) int limit) {
        List<Article> articles = this.newsService.getCurrentNews(page, limit);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}
