package com.example.rmg.usecase.subscription.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class ListSubscriptionUseCaseInput {

    private UUID studentId;

}
