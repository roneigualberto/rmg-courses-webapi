package com.example.rmg.usecase.course.lecture.retrieve;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@AllArgsConstructor
@Data
@Builder
public class RetrieveLectureMediaUseCaseOutput {

    private InputStream media;
}
