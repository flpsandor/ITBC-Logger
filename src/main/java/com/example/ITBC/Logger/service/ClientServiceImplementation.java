package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.dto.LoginDto;
import com.example.ITBC.Logger.exception.ClientNotExistException;
import com.example.ITBC.Logger.exception.RegistrationNotPosible;
import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public ClientServiceImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client register(Client client) throws RegistrationNotPosible {
        if(client.getUsername().length()<3){
            throw new RegistrationNotPosible("username at least 3 characters");
        }
        if(client.getPassword().length()<8){
            throw new RegistrationNotPosible("password at least 8 characters and one letter and one number");
        }
        return clientRepository.save(client);
    }

    @Override
    public String login(LoginDto loginDto) throws ClientNotExistException {
        var client = clientRepository.findByUsername(loginDto.getUsername());
        if (Objects.isNull(client) || !client.getPassword().equals(loginDto.getPassword())) {
            throw new ClientNotExistException("Username or password incorrect");
        }
       // TODO vratiti token i 200 OK
        return client.getUsername();
    }
}
