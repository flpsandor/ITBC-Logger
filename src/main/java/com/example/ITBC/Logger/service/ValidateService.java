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

    public boolean validateToken(String token){
        var validate = clientRepository.findByToken(token);
        boolean isTrue = true;
        if (Objects.isNull(validate)) {
            isTrue=false;
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect token");
        }
        if (!validate.getUserType().equals(UserType.ADMIN)) {
            isTrue=false;
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correct token, but not admin");
        }
        return isTrue;
    }
}
