package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.model.Log;

import java.util.UUID;

public interface LogService {
    Log createLog(Log log, UUID token);
}
