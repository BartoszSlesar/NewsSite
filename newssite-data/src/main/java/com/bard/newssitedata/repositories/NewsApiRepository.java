package com.bard.newssitedata.repositories;

import com.bard.newssitedata.model.Article;

import java.util.List;

public interface NewsApiRepository {
    List<Article> getCurrentNews();
}