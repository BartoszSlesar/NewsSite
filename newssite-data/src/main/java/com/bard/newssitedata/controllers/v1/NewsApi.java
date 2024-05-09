package com.bard.newssitedata.controllers.v1;


import com.bard.newssitedata.model.Article;
import com.bard.newssitedata.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
@CrossOrigin("*")
public class NewsApi {

    private final NewsService newsService;

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Article>> getCars() {
        List<Article> articles = this.newsService.getCurrentNews();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}
