package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.error.ClientNotExistException;
import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerRequest;

import java.net.http.HttpHeaders;
import java.util.Optional;

@Service
public class ClientServiceImplementation implements ClientService{

    @Autowired
    private final ClientRepository clientRepository;

    public ClientServiceImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client register(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public String login(String username, String password) throws ClientNotExistException {
        var clientDb = clientRepository.findByUsername(username);
        System.out.println(clientDb);
       //Optional<Client> clientDb = Optional.ofNullable((clientRepository.findByUsername(username)));
        if(!clientDb.getUsername().equals(username) && !clientDb.getPassword().equals(password)){
            throw new ClientNotExistException("username or password incorrect");
        }
        return clientDb.getUsername();
    }
}
