package com.example.rmg.usecase.course.lecture.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.InputStream;
import java.util.UUID;


@AllArgsConstructor
@Data
@Builder
public class StoreLectureMediaUseCaseInput {

    private UUID courseId;

    private UUID lectureId;

    private InputStream content;

}
