package com.example.ITBC.Logger.exception;

import com.example.ITBC.Logger.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponceEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotExistException.class)
    public ResponseEntity<Message> clientNotExistException(ClientNotExistException exception, WebRequest request) {
        Message message = new Message(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(RegistrationNotPosible.class)
    public ResponseEntity<Message> registrationNotPosible(RegistrationNotPosible exception, WebRequest request){
        Message message = new Message(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
