package com.example.rmg.domain.common.exception;

import lombok.Getter;

import java.util.Set;

@Getter
public class ValidationException extends RuntimeException {

    private final Set<ValidationError> errors;

    public ValidationException(Set<ValidationError> errors) {
        this.errors = errors;
    }
}
