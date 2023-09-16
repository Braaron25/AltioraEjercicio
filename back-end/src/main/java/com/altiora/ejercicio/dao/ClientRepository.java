package com.altiora.ejercicio.dao;

import com.altiora.ejercicio.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
