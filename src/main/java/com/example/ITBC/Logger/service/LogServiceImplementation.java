package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImplementation implements LogService{

    @Autowired
    private final LogRepository logRepository;

    public LogServiceImplementation(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Log createLog(Log log) {
        return logRepository.save(log);
    }
}
