package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/api/logs/create")
    public ResponseEntity<Log> createLog(@RequestBody @Valid Log log, @RequestHeader(value="Authorization") String token){
        return new ResponseEntity<>(logService.createLog(log, token), HttpStatus.CREATED);
    }

    @GetMapping("/api/logs/search")
    public ResponseEntity<List<Log>> searchLogs(@RequestBody Map<String, String> reqParam, @RequestHeader(value="Authorization") String token){
        return new ResponseEntity<>(logService.searchLogs(reqParam,token), HttpStatus.OK);
    }
}
