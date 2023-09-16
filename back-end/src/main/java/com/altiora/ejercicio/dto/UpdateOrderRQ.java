package com.altiora.ejercicio.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateOrderRQ {

    private String orderCode;

    private List<UpdateArticleRQ> updateArticleRQList;
}
