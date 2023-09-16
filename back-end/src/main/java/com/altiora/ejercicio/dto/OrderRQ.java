package com.altiora.ejercicio.dto;

import com.altiora.ejercicio.model.Article;
import com.altiora.ejercicio.model.Client;
import lombok.Data;

import java.util.List;

@Data
public class OrderRQ {
    private Integer clientCode;

    private List<Integer> articleList;
}
