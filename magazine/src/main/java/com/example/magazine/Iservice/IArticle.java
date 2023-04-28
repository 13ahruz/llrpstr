package com.example.magazine.Iservice;

import com.example.magazine.model.Article;

import java.util.List;

public interface IArticle {
    List<Article> getAllArticles();

    Article saveArticle(Article article);

    Article getArticleById(Long id);

    Article updateArticle(Article article);

    void deleteArticleById(Long id);
}
