package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class LogController {

    @Autowired
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/api/logs/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Log createLog(@RequestBody @Valid Log log, @RequestHeader(value="Authorization") String token){
        return logService.createLog(log, token);
    }

    @GetMapping("/api/logs/search")
    public List<Log> searchLogs(@RequestParam Map<String, String> reqParam, @RequestHeader(value="Authorization") String token){
        return logService.searchLogs(reqParam,token);
    }
}
