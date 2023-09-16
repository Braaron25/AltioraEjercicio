package com.altiora.ejercicio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orderarticle")
@TableGenerator(name="tab", initialValue=6, allocationSize=50)
public class OrderArticle {

    @Id
    @Column(name = "cod_detail", nullable = false)
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    private Integer code;

    @JoinColumn(name = "cod_order", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Order order;

    @JoinColumn(name = "cod_article", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Article article;
}
