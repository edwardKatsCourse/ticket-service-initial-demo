package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class EmailDuplicationException extends GeneralAPIException {

    private static final String DEFAULT_MESSAGE = "Such email already exists: [%s]";
    private String email;
    public EmailDuplicationException(String email) {
        this.email = email;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    protected String getDefaultMessage() {
        return String.format(DEFAULT_MESSAGE, this.email);
    }
}
