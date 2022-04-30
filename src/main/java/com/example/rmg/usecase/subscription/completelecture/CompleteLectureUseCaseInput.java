package com.example.rmg.usecase.subscription.completelecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class CompleteLectureUseCaseInput {

    private UUID subscriptionId;

    private UUID lectureId;
}
