package com.example.ITBC.Logger.error;

import com.example.ITBC.Logger.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.ErrorManager;

@ControllerAdvice
@ResponseStatus
public class RestResponceEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotExistException.class)
    public ResponseEntity<ErrorMessage> clientNotExistException(ClientNotExistException exception, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
