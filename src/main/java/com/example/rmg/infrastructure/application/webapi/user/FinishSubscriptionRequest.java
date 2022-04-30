package com.example.rmg.infrastructure.application.webapi.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FinishSubscriptionRequest {

    private Integer rating;

    private String comment;
}
