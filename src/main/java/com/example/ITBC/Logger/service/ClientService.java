package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.dto.LoginDto;
import com.example.ITBC.Logger.dto.PasswordDto;
import com.example.ITBC.Logger.model.Client;

import java.util.List;

public interface ClientService {
    Client register(Client client);

    String login(LoginDto loginDto);

    List<Client> getAllClient(String token);

    Client passwordChange(Long id, PasswordDto password, String token);
}
