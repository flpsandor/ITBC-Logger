package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientControler {

    @Autowired
    private final ClientService clientService;

    public ClientControler(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/api/clients/register")
    public Client register(@RequestBody Client client){
        return clientService.register(client);
    }
}
