package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @Autowired
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/api/logs/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Log createLog(@RequestBody Log log){
        return logService.createLog(log);
    }
}
