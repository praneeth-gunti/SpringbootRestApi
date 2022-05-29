package com.example.agpaytech.exception;

public class InvalidParamData extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InvalidParamData(String message) {
        super(message);
    }

    public InvalidParamData(String message, Throwable cause) {
        super(message, cause);
    }
}
