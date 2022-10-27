package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.model.Log;

import java.util.List;
import java.util.Map;

public interface LogService {
    Log createLog(Log log, String token);

    List<Log> searchLogs(Map<String, String> reqParam, String token);

    Boolean deleteLog(Long id, String token);
}
