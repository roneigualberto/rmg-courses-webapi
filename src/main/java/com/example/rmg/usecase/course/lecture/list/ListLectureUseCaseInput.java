package com.example.rmg.usecase.course.lecture.list;


import com.example.rmg.usecase.course.lecture.common.input.LectureForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class ListLectureUseCaseInput {

    private UUID courseId;

}
