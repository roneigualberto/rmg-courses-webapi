package com.example.rmg.usecase.course.find;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class FindCourseUseCaseInput {

    private UUID courseId;

}
