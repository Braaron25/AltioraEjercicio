package com.altiora.ejercicio.service.implementation;

import com.altiora.ejercicio.dao.ClientRepository;
import com.altiora.ejercicio.dto.ClientRQ;
import com.altiora.ejercicio.model.Client;
import com.altiora.ejercicio.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients(){
        return this.clientRepository.findAll();
    }

    @Override
    public void addNewClient(ClientRQ clientRQ){
        Client client = new Client();
        client.setFirstName(clientRQ.getFirstName());
        client.setLastName(clientRQ.getLastName());
        this.clientRepository.save(client);
    }
}
