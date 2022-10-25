package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.model.LogType;
import com.example.ITBC.Logger.repository.ClientRepository;
import com.example.ITBC.Logger.repository.LogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.UUID;

@Service
public class LogServiceImplementation implements LogService {

    private final LogRepository logRepository;
    private final ClientRepository clientRepository;

    public LogServiceImplementation(LogRepository logRepository, ClientRepository clientRepository) {
        this.logRepository = logRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Log createLog(Log log, UUID token) {
        if(log.getMessage().length()>1024){
            throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "Message should be less than 1024");
        }
        boolean exist = false;
        for(var type: LogType.values()){
            if (type.equals(log.getLogType())) {
                exist = true;
                break;
            }
        }
        if(!exist)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect logType");

        if(Objects.nonNull(clientRepository.findByToken(token))){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect token");
        }
        log.setToken(token);
        return logRepository.save(log);
    }
}
