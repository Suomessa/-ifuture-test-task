package com.server.exception;

public class OperationFailedException extends Exception {
    public OperationFailedException(String errorMessage) {
        super(errorMessage);
    }
}
