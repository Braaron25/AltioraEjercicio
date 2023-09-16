package com.altiora.ejercicio.dao;

import com.altiora.ejercicio.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
