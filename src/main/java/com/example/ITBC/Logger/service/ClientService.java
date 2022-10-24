package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.dto.LoginDto;
import com.example.ITBC.Logger.exception.ClientNotExistException;
import com.example.ITBC.Logger.exception.RegistrationNotPosible;
import com.example.ITBC.Logger.model.Client;


public interface ClientService {
    Client register(Client client) throws RegistrationNotPosible;

    String login(LoginDto loginDto) throws ClientNotExistException;
}
