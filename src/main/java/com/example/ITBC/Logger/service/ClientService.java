package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.dto.LoginDto;
import com.example.ITBC.Logger.exception.ClientNotExistException;
import com.example.ITBC.Logger.exception.RegistrationNotPosible;
import com.example.ITBC.Logger.model.Client;
import org.springframework.http.ResponseEntity;


public interface ClientService {
    Client register(Client client) throws RegistrationNotPosible;

    ResponseEntity.BodyBuilder login(LoginDto loginDto) throws ClientNotExistException;
}
