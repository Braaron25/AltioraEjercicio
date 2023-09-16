package com.altiora.ejercicio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order")
public class Order {

    @Id
    @Column(name = "cod_order", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String code;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    @JsonManagedReference
    private List<OrderArticle> orderArticleList;

    @JoinColumn(name = "cod_cliente", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

}
