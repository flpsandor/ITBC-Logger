package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.error.ClientNotExistException;
import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/api/clients/login")
    public String login(@RequestBody String username, String password) throws ClientNotExistException {
        System.out.println(username+" "+password);
        return clientService.login(username, password);
    }
}
