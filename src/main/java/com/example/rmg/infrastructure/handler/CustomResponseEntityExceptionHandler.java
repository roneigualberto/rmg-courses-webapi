package com.example.rmg.infrastructure.handler;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.common.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    private final HttpServletRequest httpServletRequest;


    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public final ResponseEntity<ApiResponse> handleDomainException(DomainException ex, WebRequest request) {


        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ApiResponse body = ApiResponse.builder()
                .timestamp(new Date().getTime())
                .error(httpStatus.getReasonPhrase())
                .path(httpServletRequest.getRequestURI())
                .message(ex.getMessage())
                .status(httpStatus.value())
                .build();

        return ResponseEntity.status(httpStatus).body(body);

    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public final ResponseEntity<Map<String, Object>> handleDomainException(ValidationException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();

        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        body.put("timestamp", new Date().getTime());
        body.put("message", "Validation Errors");
        body.put("errors", ex.getErrors());
        body.put("path", httpServletRequest.getRequestURI());
        body.put("status", String.valueOf(httpStatus.value()));

        return ResponseEntity.status(httpStatus).body(body);

    }


}
