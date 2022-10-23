package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.model.Client;
import org.springframework.stereotype.Service;


public interface ClientService {
    Client register(Client client);
}
