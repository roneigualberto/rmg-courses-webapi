package com.example.rmg.usecase.course.lecture.retrieve;

import com.example.rmg.usecase.course.lecture.common.output.LectureView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@AllArgsConstructor
@Data
@Builder
public class RetrieveLectureMediaUseCaseOutput {


    private LectureView lecture;

    private InputStream media;

}
