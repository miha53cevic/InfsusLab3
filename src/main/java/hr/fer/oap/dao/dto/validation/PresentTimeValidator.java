package hr.fer.oap.dao.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class PresentTimeValidator implements ConstraintValidator<PresentTime, LocalDateTime> {

    @Override
    public void initialize(PresentTime constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let other validators handle null values
        }
        LocalDateTime currentTime = LocalDateTime.now();
        return value.isAfter(currentTime) || value.isEqual(currentTime);
    }
}

