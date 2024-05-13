package com.bard.newssitedata.repositories;

import com.bard.newssitedata.model.Article;

import java.time.LocalDate;
import java.util.List;

public interface NewsApiRepository {
    List<Article> getCurrentNews(String topic, int page, int limit);
}
