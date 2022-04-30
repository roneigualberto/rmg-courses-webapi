package com.example.rmg.usecase.subscription.common.output;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class SubscriptionView {

    private UUID id;

    private CourseView course;

    private UUID studentId;
}
