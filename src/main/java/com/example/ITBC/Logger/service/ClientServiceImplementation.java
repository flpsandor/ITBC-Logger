package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.dto.LoginDto;
import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Objects;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public ClientServiceImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client register(Client client) {
        for(var c : clientRepository.findAll()){
            if (c.getUsername().equals(client.getUsername()) || c.getEmail().equals(client.getEmail())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "username or email alredy exist");
            }
        }
        // TODO with validator
        return clientRepository.save(client);
    }

    @Override
    public String login(LoginDto loginDto){
        var client = clientRepository.findByUsername(loginDto.getUsername());
        if (Objects.isNull(client) || !client.getPassword().equals(loginDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or password incorect");
        }
        var token = client.getUserType();
        // TODO set token in header
        return token.toString();
    }

    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }
}
