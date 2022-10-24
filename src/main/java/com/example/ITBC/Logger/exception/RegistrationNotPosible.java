package com.example.ITBC.Logger.exception;

public class RegistrationNotPosible extends Exception{
    public RegistrationNotPosible() {
        super();
    }

    public RegistrationNotPosible(String message) {
        super(message);
    }

    public RegistrationNotPosible(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationNotPosible(Throwable cause) {
        super(cause);
    }

    protected RegistrationNotPosible(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
