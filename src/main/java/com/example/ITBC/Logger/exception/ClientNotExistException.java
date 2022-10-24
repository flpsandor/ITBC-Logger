package com.example.ITBC.Logger.exception;

public class ClientNotExistException extends Exception{

    public ClientNotExistException() {
        super();
    }

    public ClientNotExistException(String message) {
        super(message);
    }

    public ClientNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotExistException(Throwable cause) {
        super(cause);
    }

    protected ClientNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
