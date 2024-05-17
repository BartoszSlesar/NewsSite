package com.bard.newssitedata.utils;

import com.bard.newssitedata.model.Article;
import com.bard.newssitedata.model.News;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArticleNewsConverter {


    public List<News> convertArticles(List<Article> articles) {
        List<News> newsTmp = new ArrayList<>();

        for (Article article : articles) {
            News news = new News();
            news.setNewsId(article.getTitle().toLowerCase().replaceAll("\\s+", ""));
            news.setAuthor(article.getAuthor());
            news.setContent(article.getContent());
            news.setDescription(article.getDescription());
            news.setTitle(article.getTitle());
            news.setSource(article.getSource());
            news.setUrl(article.getUrl());
            news.setUrlToImage(article.getUrlToImage());
            news.setPublishedAt(convertDate(article.getPublishedAt()));

            newsTmp.add(news);

        }

        return newsTmp;
    }

    private String convertDate(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        DateTimeFormatter currentDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDateTime.format(currentDateFormatter);
    }
}
