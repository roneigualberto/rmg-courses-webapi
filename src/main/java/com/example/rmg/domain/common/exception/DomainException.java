package com.example.rmg.domain.common.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

}
