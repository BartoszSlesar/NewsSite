package com.bard.newssitedata.repositories;

import com.bard.newssitedata.model.Article;

import java.util.List;




public interface DatabaseNewsRepository {



    List<Article> getArticles();

    void saveArticle(Article article);

    void saveArticle(List<Article> articles);

    void updateArticle(Article article);

    void deleteArticle(long id);


}
