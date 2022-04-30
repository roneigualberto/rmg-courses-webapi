package com.example.rmg.usecase.subscription.finish;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class FinishSubscriptionUseCaseInput {


    private UUID studentId;

    private UUID subscriptionId;

    private Integer rating;

    private String comment;
}
