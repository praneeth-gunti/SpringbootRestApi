package com.example.agpaytech.exception;

public class IllegalValueException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IllegalValueException(String message) {
        super(message);
    }

    public IllegalValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
