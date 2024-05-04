package com.bard.newssitedata.repositories;

import com.bard.newssitedata.model.Article;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.List;


@Repository
public class NewsApiRepositoryImpl implements NewsApiRepository {

    private final RestClient restClient;

    public NewsApiRepositoryImpl(){
        this.restClient = RestClient.create();
    }

    @Override
    public List<Article> getCurrentNews() {
        return null;
    }
}
