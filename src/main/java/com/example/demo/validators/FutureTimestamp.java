package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Java Core
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

//javax.validation
@Constraint(validatedBy = FutureTimestampConstraintValidator.class)
public @interface FutureTimestamp {

    String message() default "Timestamp must be future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
