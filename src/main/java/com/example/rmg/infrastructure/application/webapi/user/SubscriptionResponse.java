package com.example.rmg.infrastructure.application.webapi.user;


import com.example.rmg.infrastructure.application.webapi.course.CourseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class SubscriptionResponse {

    private UUID id;

    private CourseResponse course;

    private UUID studentId;
}
