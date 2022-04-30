package com.example.rmg.usecase.subscription.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class CreateSubscriptionUseCaseInput {

    private UUID studentId;

    private Set<UUID> courseIds;
}
