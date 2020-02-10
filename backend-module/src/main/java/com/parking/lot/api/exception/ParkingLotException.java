package com.parking.lot.api.exception;

public class ParkingLotException extends RuntimeException {

    public ParkingLotException(String message) {
        super(message);
    }

    public ParkingLotException(String message, Throwable t) {
        super(message, t);
    }
}
