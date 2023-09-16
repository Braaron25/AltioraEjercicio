package com.altiora.ejercicio.dao;

import com.altiora.ejercicio.model.OrderArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderArticleRepository extends JpaRepository<OrderArticle, Integer> {
}
