package com.altiora.ejercicio.service;

import com.altiora.ejercicio.dto.ClientRQ;
import com.altiora.ejercicio.model.Client;

import java.util.List;

public interface IClientService {
    List<Client> getAllClients();

    void addNewClient(ClientRQ clientRQ);
}
