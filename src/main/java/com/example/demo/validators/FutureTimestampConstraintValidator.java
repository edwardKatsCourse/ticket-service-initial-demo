package com.example.demo.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class FutureTimestampConstraintValidator implements ConstraintValidator<FutureTimestamp, Long> {

    //"constructor"

    @Override
    public void initialize(FutureTimestamp constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        //Long (timestamp) -> LocalDateTime
        LocalDateTime inputValue = LocalDateTime.ofInstant(Instant.ofEpochSecond(value), ZoneId.systemDefault());

        return inputValue.isAfter(currentDateTime);
    }
}
