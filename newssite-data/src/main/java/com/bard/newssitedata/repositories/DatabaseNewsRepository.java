package com.bard.newssitedata.repositories;

import com.bard.newssitedata.model.Article;
import com.bard.newssitedata.model.ArticlesPages;

import java.util.List;




public interface DatabaseNewsRepository {



    ArticlesPages getArticles(int page, int limit);

    void saveArticle(Article article);

    void saveArticle(List<Article> articles);

    void updateArticle(Article article);

    void deleteArticle(long id);


}
