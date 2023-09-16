package com.altiora.ejercicio.dao;

import com.altiora.ejercicio.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
