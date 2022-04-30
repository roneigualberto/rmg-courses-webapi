package com.example.rmg.infrastructure.handler;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ApiResponse {


    private long timestamp;

    private String path;

    private String message;

    private String error;

    private int status;


}
