package com.example.magazine.controller;

import com.example.magazine.Iservice.IArticle;
import com.example.magazine.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    private final IArticle iArticle;

    public ArticleController(IArticle iArticle) {
        super();
        this.iArticle = iArticle;
    }

    @GetMapping("/articles")
    public String listArticles(Model model) {
        model.addAttribute("articles", iArticle.getAllArticles());
        return "articles";
    }

    @GetMapping("/articles/new")
    public String createArticle(Model model) {
        Article article = new Article();
        model.addAttribute("article", article);
        return "create_article";
    }

    @PostMapping("/articles")
    public String addArticle(@ModelAttribute("article") Article article) {
        iArticle.saveArticle(article);
        return "redirect:/articles";
    }

    @GetMapping("/articles/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", iArticle.getArticleById(id));
        return "edit_article";
    }

    @PostMapping("/articles/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute("article") Article article) {
        Article existingArticle = iArticle.getArticleById(id);
        existingArticle.setId(id);
        existingArticle.setName(article.getName());
        existingArticle.setDescription(article.getDescription());

        iArticle.updateArticle(existingArticle);
        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String deleteArticle(@PathVariable Long id) {
        iArticle.deleteArticleById(id);
        return "redirect:/articles";
    }
}
