package com.altiora.ejercicio.service.implementation;

import com.altiora.ejercicio.dao.ArticleRepository;
import com.altiora.ejercicio.model.Article;
import com.altiora.ejercicio.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles() {
        return this.articleRepository.findAll();
    }

    @Override
    public void addNewArticle(Article article) {
        this.articleRepository.save(article);
    }
}
