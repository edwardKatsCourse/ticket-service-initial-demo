package com.example.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TicketServiceExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> getError(Exception e) {

        Map<String, String> map = new HashMap<>();
        map.put("error", e.getMessage());
        map.put("code", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));

        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
