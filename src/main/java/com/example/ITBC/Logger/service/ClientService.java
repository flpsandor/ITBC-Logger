package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.dto.LoginDto;
import com.example.ITBC.Logger.model.Client;
import org.springframework.http.server.ServerHttpResponse;

import java.util.List;

public interface ClientService {
    Client register(Client client);

    String login(LoginDto loginDto);

    List<Client> getAllClient();
}
