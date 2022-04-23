package com.example.rmg.usecase.course.publish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class PublishCourseUseCaseInput {

    private UUID courseId;
}
