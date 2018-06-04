package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralAPIException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Unexpected error occurred";

    public GeneralAPIException() {
    }

    public GeneralAPIException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    protected String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null || super.getMessage().isEmpty()) {

            return getDefaultMessage();
        }

        return super.getMessage();
    }
}
