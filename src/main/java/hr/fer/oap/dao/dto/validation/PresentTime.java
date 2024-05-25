package hr.fer.oap.dao.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PresentTimeValidator.class)
public @interface PresentTime {

    String message() default "Time must be in the present or future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
