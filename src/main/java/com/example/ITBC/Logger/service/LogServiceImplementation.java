package com.example.ITBC.Logger.service;

import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.model.LogType;
import com.example.ITBC.Logger.repository.ClientRepository;
import com.example.ITBC.Logger.repository.LogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class LogServiceImplementation implements LogService {

    private final LogRepository logRepository;
    private final ValidateService validateService;
    private final ClientRepository clientRepository;

    public LogServiceImplementation(LogRepository logRepository, ValidateService validateService, ClientRepository clientRepository) {
        this.logRepository = logRepository;
        this.validateService = validateService;
        this.clientRepository = clientRepository;
    }

    @Override
    public Log createLog(Log log, String token) {
        if (log.getMessage().length() > 1024) {
            throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "Message should be less than 1024");
        }
        boolean exist = false;
        for (var type : LogType.values()) {
            if (type.equals(log.getLogType())) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect logType");
        }
        if (Objects.isNull(clientRepository.findByToken(token))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect token");
        }
        var client = clientRepository.findByToken(token);
        log.setClientId(client.getId());
        // log count, not so good implementation
        var count = client.getLogCount();
        count++;
        client.setLogCount(count);

        return logRepository.save(log);
    }

    @Override
    public List<Log> searchLogs(Map<String, String> reqParam, String token) {
        String message = null;
        LogType logType = null;
        LocalDateTime dateFrom = null;
        LocalDateTime dateTo = null;
        if(Objects.nonNull(reqParam.get("message"))){
            message = reqParam.get("message");
        }
        if(Objects.nonNull(reqParam.get("logType"))){
            logType = LogType.valueOf(reqParam.get("logType"));
        }
        if(Objects.nonNull(reqParam.get("dateFrom"))){
            dateFrom = LocalDateTime.parse(reqParam.get("dateFrom"));
        }
        if(Objects.nonNull(reqParam.get("dateTo"))){
            dateTo = LocalDateTime.parse(reqParam.get("dateTo"));
        }
        validateService.validateUser(token);
        return logRepository.findLogsByParam(message,logType,dateFrom, dateTo);
    }

    @Override
    public Boolean deleteLog(Long id, String token) {
        if (validateService.validateAdmin(token)) {
            if (Objects.isNull(logRepository.findById(id))) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Log not found");
            }
            logRepository.deleteById(id);
        }
        return true;
    }
}
