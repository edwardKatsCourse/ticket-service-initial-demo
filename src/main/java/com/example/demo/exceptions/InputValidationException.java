package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class InputValidationException extends GeneralAPIException {
    private static final String DEFAULT_MESSAGE = "Input validation error occurred";

    private BindingResult result;



    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    public Map<String, List<String>> getInputValidationErrors() {
        return this.result
                .getFieldErrors()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                FieldError::getField,
                                Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

    }

    @Override
    protected String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}
