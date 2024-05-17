package com.bard.newssitedata.repositories;

import com.bard.newssitedata.model.News;

import java.util.List;


public interface DatabaseNewsRepository {


    List<News> getNews(int page, int limit);


    List<News> saveNews(List<News> articles);

    void updateNews(News news);

    void deleteNews(long id);


}
