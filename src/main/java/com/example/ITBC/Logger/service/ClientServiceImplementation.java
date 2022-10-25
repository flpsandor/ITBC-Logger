package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.dto.LoginDto;
import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.repository.ClientRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class ClientServiceImplementation implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client register(Client client) {
        var clientDb = clientRepository.findByUsernameOrEmail(client.getUsername(), client.getEmail());
        if(!Objects.isNull(clientDb)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "username or email already exist");
        }
        return clientRepository.save(client);
    }

    @Override
    public String login(LoginDto loginDto) {
        var client = clientRepository.findByUsername(loginDto.getUsername());
        if (Objects.isNull(client) || !client.getPassword().equals(loginDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or password incorrect");
        }
        return client.getToken().toString();
    }

    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }
}
