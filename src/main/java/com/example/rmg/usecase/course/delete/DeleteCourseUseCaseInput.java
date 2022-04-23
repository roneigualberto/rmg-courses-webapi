package com.example.rmg.usecase.course.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class DeleteCourseUseCaseInput {

    private UUID courseId;
}
