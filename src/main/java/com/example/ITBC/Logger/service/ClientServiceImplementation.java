package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
