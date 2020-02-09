package com.parking.lot.api.exception;

public class BatchJobException extends RuntimeException {

    public BatchJobException(String message) {
        super(message);
    }

    public BatchJobException(String message, Throwable t) {
        super(message, t);
    }
}
