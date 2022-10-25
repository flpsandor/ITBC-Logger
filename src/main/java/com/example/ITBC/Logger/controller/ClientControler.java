package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.dto.LoginDto;
import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<Client> register(@RequestBody @Valid Client client){
        return new ResponseEntity<>(clientService.register(client), HttpStatus.CREATED);
    }

    @PostMapping("/api/clients/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto){
        return new ResponseEntity<>(clientService.login(loginDto), HttpStatus.OK);
    }

    @GetMapping("/api/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClient(){
        return clientService.getAllClient();
    }
}
