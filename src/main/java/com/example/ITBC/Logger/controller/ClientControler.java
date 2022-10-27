package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.dto.LoginDto;
import com.example.ITBC.Logger.dto.PasswordDto;
import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientControler {

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
    public ResponseEntity<List<Client>> getAllClient(@RequestHeader(value="Authorization") String token){
        return new ResponseEntity<>(clientService.getAllClient(token), HttpStatus.OK);
    }

    @PatchMapping("/api/clients/{id}/reset-password")
    public ResponseEntity<Client> passwordChange(@RequestBody PasswordDto password, @RequestHeader(value="Authorization") String token, @PathVariable("id") Long id){
        return new ResponseEntity<>(clientService.passwordChange(id,password,token),HttpStatus.CREATED);
    }

    @DeleteMapping("/api/clients/{id}/delete-cleint")
    public ResponseEntity<Boolean> deleteClient(@PathVariable("id") Long id, @RequestHeader(value="Authorization") String token){
        return new ResponseEntity<>(clientService.deleteUser(id, token), HttpStatus.OK);
    }
}
