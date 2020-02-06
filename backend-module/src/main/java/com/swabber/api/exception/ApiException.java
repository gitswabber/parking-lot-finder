package com.swabber.api.exception;

// todo
public class ApiException extends RuntimeException {
    private int httpCode;

    public ApiException(int httpCode, String message) {
        super(message);
        this.httpCode = httpCode;
    }

    public ApiException(int httpCode, String message, Throwable t) {
        super(message, t);
        this.httpCode = httpCode;
    }
}
