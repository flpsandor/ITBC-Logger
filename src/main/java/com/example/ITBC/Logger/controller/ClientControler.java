package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.dto.LoginDto;
import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientControler {

    @Autowired
    private final ClientService clientService;

    public ClientControler(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/api/clients/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Client register(@Valid @RequestBody Client client){
        return clientService.register(client);
    }

    @PostMapping("/api/clients/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginDto loginDto){
        return clientService.login(loginDto);
    }

    @GetMapping("/api/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClient(){
        return clientService.getAllClient();
    }
}
