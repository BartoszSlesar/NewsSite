package com.bard.newssitedata.utils;

import com.bard.newssitedata.model.Article;
import com.bard.newssitedata.model.News;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ArticleNewsConverter {

    private static final String URL_PATTERN = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";

    public List<News> convertArticles(List<Article> articles) {
        List<News> newsTmp = new ArrayList<>();
        for (Article article : articles) {
            News news = new News();
            news.setNewsId(article.getTitle().toLowerCase().replaceAll("\\s+", ""));

            String author = article.getAuthor() == null ? "" : article.getAuthor();
            news.setAuthor(author.replaceAll(URL_PATTERN, ""));
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

        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        DateTimeFormatter currentDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDateTime.format(currentDateFormatter);
    }
}
