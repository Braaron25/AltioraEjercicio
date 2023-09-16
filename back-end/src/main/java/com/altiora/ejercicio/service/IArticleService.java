package com.altiora.ejercicio.service;

import com.altiora.ejercicio.model.Article;

import java.util.List;

public interface IArticleService {

    List<Article> getAllArticles();

    void addNewArticle(Article article);
}
