package com.example.rmg.domain.common.validator;

import com.example.rmg.domain.common.exception.ValidationException;
import com.example.rmg.domain.common.exception.ValidationError;

import javax.validation.*;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidatorUtil {


    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    public static <T> void validate(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);

        if (!violations.isEmpty()) {
            Set<ValidationError> errors = violations.stream().map((violation) ->
                    ValidationError.builder(
                            ).field(getField(violation))
                            .message(violation.getMessage())
                            .build()
            ).collect(Collectors.toSet());

            throw new ValidationException(errors);
        }

    }

    private static <T> String getField(ConstraintViolation<T> violation) {
        String field = null;
        for (Path.Node node : violation.getPropertyPath()) {
            field = node.getName();
        }
        return field;
    }
}
