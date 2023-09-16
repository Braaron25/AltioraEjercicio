package com.altiora.ejercicio.controller;

import com.altiora.ejercicio.model.Article;
import com.altiora.ejercicio.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/article")
@Slf4j
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @GetMapping(path = "/getAllArticles")
    public ResponseEntity getAllArticles(){
        try {
            return ResponseEntity.ok(this.articleService.getAllArticles());
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/createArticle")
    public ResponseEntity addNewArticle(@RequestBody Article article){
        try {
            this.articleService.addNewArticle(article);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
