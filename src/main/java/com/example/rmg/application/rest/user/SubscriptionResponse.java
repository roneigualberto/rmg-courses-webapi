package com.example.rmg.application.rest.user;


import com.example.rmg.application.rest.course.CourseResponse;
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
