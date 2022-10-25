package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.dto.LoginDto;
import com.example.ITBC.Logger.dto.PasswordDto;
import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.model.UserType;
import com.example.ITBC.Logger.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ClientServiceImplementation implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client register(Client client) {
        var clientDb = clientRepository.findByUsernameOrEmail(client.getUsername(), client.getEmail());
        if (!Objects.isNull(clientDb)) {
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
        client.setToken(String.valueOf(UUID.randomUUID()));
        clientRepository.save(client);
        return client.getToken();
        // TODO generate token on login, not on registration
    }

    @Override
    public List<Client> getAllClient(String token) {
        var clientDb = clientRepository.findByToken(token);
        if (Objects.isNull(clientDb)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect token");
        }
        if (!clientDb.getUserType().equals(UserType.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correct token, but not admin");
        }
        return clientRepository.findAll();
    }

    @Override
    public Client passwordChange(Long id, PasswordDto password, String token) {
        var validate = clientRepository.findByToken(token);
        if (Objects.isNull(validate)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect token");
        }
        if (!validate.getUserType().equals(UserType.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correct token, but not admin");
        }
        var clientDb = clientRepository.findById(id).get();
        clientDb.setPassword(password.getPassword());
        return clientRepository.save(clientDb);
    }
}
