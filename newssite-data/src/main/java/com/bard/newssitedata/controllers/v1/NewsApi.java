package com.bard.newssitedata.controllers.v1;


import com.bard.newssitedata.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
@CrossOrigin("*")
public class NewsApi {

    private final NewsService newsService;
}
