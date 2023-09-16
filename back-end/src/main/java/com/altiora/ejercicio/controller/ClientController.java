package com.altiora.ejercicio.controller;

import com.altiora.ejercicio.dto.ClientRQ;
import com.altiora.ejercicio.service.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/client")
@Slf4j
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping(path = "/getAllClients")
    public ResponseEntity getAllClients(){
        try {
            return ResponseEntity.ok(this.clientService.getAllClients());
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/createClient")
    public ResponseEntity addNewClient(@RequestBody ClientRQ clientRQ){
        try {
            this.clientService.addNewClient(clientRQ);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
