package com.example.demo.handler;

import com.example.demo.exceptions.GeneralAPIException;
import com.example.demo.exceptions.InputValidationException;
import com.example.demo.model.web.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class TicketServiceExceptionHandler {


    /**
     * A
     * B extends A
     * C extends B
     *
     *
     * method(C c)
     * method(B b)
     * method(A a)
     *
     *
     * GrandFather
     * Father
     * Child
     * GrandChild
     *
     * method(GrandFather)
     * method(Father) <--- this method will be called
     *
     * throw new Child()
     * throw new GrandChild()
     *
     *
     * Exception <--- method
     * RuntimeException
     *
     * GeneralAPIException <--- method
     * EmailDuplicationException
     *
     * throw new EmailDuplicationException()
     */


    /**
     *
     * Informal split onto: things we can control and things we CANNOT control
     *
     * Informal split onto: things user CAN fix and the things user CANNOT fix (DB connection error)
     *
     */

    //@RestController -> starting from Spring 4!!
    //@ResponseBody
    //Object

    //ResponseEntity<what to convert to JSON> methodName() {}


    //404, 500, 403, 401, 400 .... Legion....
    @ExceptionHandler(GeneralAPIException.class)
    public ResponseEntity<ErrorResponse> getApiErrorResponse(GeneralAPIException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage());

        if (exception instanceof InputValidationException) {
            InputValidationException ive = (InputValidationException) exception;
            Map<String, List<String>> errors = ive.getInputValidationErrors();
            response.setBody(errors);
        }

        return new ResponseEntity<>(response, exception.getStatus());
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> getError(Exception e) {

        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
