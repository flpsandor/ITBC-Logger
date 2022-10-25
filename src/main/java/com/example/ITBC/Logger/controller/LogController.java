package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
public class LogController {

    @Autowired
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/api/logs/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Log createLog(@RequestBody @Valid Log log, @RequestHeader(value="Authorization") UUID token){
        return logService.createLog(log, token);
    }
}
