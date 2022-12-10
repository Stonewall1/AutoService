package com.autoservice.exception;

public class MasterNotFoundException extends RuntimeException {
    public static final String MASTER_NOT_FOUND = "Master not found!";

    public MasterNotFoundException() {
    }

    public MasterNotFoundException(String message) {
        super(message);
    }

    public MasterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MasterNotFoundException(Throwable cause) {
        super(cause);
    }

    public MasterNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return MASTER_NOT_FOUND;
    }
}
