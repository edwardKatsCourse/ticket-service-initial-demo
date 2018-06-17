package com.example.demo.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DayDifferenceTimestampConstraintValidator implements ConstraintValidator<DayDifferenceTimestamp, Long> {

    private int days;

    @Override
    public void initialize(DayDifferenceTimestamp constraintAnnotation) {
        this.days = constraintAnnotation.days();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        LocalDateTime currentDateTime = LocalDateTime.now();


        LocalDateTime inputValue = LocalDateTime.ofInstant(Instant.ofEpochSecond(value), ZoneId.systemDefault());

        //set both currentDate and inputValue to the beginning of the day and compare using isEqual

        //If the same day, month and year - then false

        //including days via this.days
        return true;
    }
}
