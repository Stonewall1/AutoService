package com.autoservice.exception;

public class CarNotFoundException extends RuntimeException {
    public static final String CAR_NOT_FOUND = "Car not found!";

    public CarNotFoundException() {
    }

    public CarNotFoundException(String message) {
        super(message);
    }

    public CarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarNotFoundException(Throwable cause) {
        super(cause);
    }

    public CarNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return CAR_NOT_FOUND;
    }
}
