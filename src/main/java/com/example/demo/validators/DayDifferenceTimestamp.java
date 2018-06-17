package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

@Constraint(validatedBy = DayDifferenceTimestampConstraintValidator.class)
public @interface DayDifferenceTimestamp {

    String message() default "Day difference is not enough";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int days();
}
