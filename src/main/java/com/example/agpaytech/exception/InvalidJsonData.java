package com.example.agpaytech.exception;

public class InvalidJsonData extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidJsonData(String message) {
        super(message);
    }

    public InvalidJsonData(String message, Throwable cause) {
        super(message, cause);
    }
}
