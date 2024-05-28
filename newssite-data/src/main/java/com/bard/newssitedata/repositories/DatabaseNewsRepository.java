package com.bard.newssitedata.repositories;

import com.bard.newssitedata.model.News;

import java.util.List;


public interface DatabaseNewsRepository {


    List<News> getNews(int page, int limit, String date);

    News getNewsById(long id);


    List<News> saveNews(List<News> articles);

    boolean updateNews(News news);

    boolean deleteNews(long id);


}
