package com.aburakkontas.manga_auth.application.validators;

import com.aburakkontas.manga_auth.application.commands.RegisterCommand;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Validable {

    public List<String> validate() {
        List<String> errorMessages = new ArrayList<>();

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Validable>> violations = validator.validate(this);
            for (ConstraintViolation<Validable> violation : violations) {
                errorMessages.add(violation.getMessage());
            }
        }
        return errorMessages;
    }

    public boolean isValid() {
        return validate().isEmpty();
    }

    public void throwIfInvalid() {
        List<String> violations = validate();
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Invalid command: " + violations);
        }
    }
}
