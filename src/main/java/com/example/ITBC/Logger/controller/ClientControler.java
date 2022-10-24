package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.dto.LoginDto;
import com.example.ITBC.Logger.exception.ClientNotExistException;
import com.example.ITBC.Logger.exception.RegistrationNotPosible;
import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ClientControler {

    @Autowired
    private final ClientService clientService;

    public ClientControler(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/api/clients/register")
    public Client register(@Valid @RequestBody Client client) throws RegistrationNotPosible {
        return clientService.register(client);
    }

    @PostMapping("/api/clients/login")
    public String login(@RequestBody LoginDto loginDto) throws ClientNotExistException {
        return clientService.login(loginDto);
    }
}
