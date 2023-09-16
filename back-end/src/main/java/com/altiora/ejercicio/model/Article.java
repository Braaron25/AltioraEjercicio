package com.altiora.ejercicio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "article")
@TableGenerator(name="tab", initialValue=6, allocationSize=50)
public class Article {

    @Id
    @Column(name = "cod_article", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator="tab")
    private Integer code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Float price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    @JsonIgnore
    private List<OrderArticle> orderArticleList;
}
