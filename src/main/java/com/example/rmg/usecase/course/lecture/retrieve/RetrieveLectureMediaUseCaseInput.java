package com.example.rmg.usecase.course.lecture.retrieve;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@AllArgsConstructor
@Data
@Builder
public class RetrieveLectureMediaUseCaseInput {

    private UUID courseId;

    private UUID lectureId;
}
