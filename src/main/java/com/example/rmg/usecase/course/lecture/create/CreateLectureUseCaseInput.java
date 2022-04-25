package com.example.rmg.usecase.course.lecture.create;


import com.example.rmg.usecase.course.lecture.common.input.LectureForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class CreateLectureUseCaseInput {

    private UUID courseId;

    private LectureForm lecture;

}
