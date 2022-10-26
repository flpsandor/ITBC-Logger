package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.model.UserType;
import com.example.ITBC.Logger.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class ValidateService {

    private final ClientRepository clientRepository;

    public ValidateService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean validateAdmin(String token){
        var client = clientRepository.findByToken(token);
        boolean isTrue = true;
        validateUser(token);
        if (!client.getUserType().equals(UserType.ADMIN)) {
            isTrue=false;
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correct token, but not admin");
        }
        return isTrue;
    }

    public boolean validateUser(String token){
        var client = clientRepository.findByToken(token);
        boolean isTrue = true;
        if(Objects.isNull(client)){
            isTrue=false;
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect token");
        }
        return isTrue;
    }
}
