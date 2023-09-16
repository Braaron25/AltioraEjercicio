package com.altiora.ejercicio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "client")
@TableGenerator(name="tab", initialValue=6, allocationSize=50)
public class Client {

    @Id
    @Column(name = "cod_cliente", nullable = false)
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    private Integer code;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonIgnore
    private List<Order> orderList;
}
