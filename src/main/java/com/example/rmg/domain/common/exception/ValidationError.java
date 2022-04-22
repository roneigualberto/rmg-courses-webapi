package com.example.rmg.domain.common.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ValidationError {

    private String field;

    private String message;
}
