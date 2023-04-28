package com.example.magazine.service;

import com.example.magazine.Iservice.IArticle;
import com.example.magazine.model.Article;
import com.example.magazine.repo.ArticleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements IArticle {
    private final ArticleRepo articleRepo;

    public ArticleService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }
    @Override
    public Article saveArticle(Article article) {
        return articleRepo.save(article);
    }

    @Override
    public Article getArticleById(Long id){
        return articleRepo.findById(id).get();
    }

    @Override
    public Article updateArticle(Article article){
        return articleRepo.save(article);
    }

    @Override
    public void deleteArticleById(Long id){
        articleRepo.deleteById(id);
    }
}
