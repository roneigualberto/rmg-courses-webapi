package com.example.rmg.usecase.course.lecture.common.input;

import com.example.rmg.domain.course.valueobject.LectureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class LectureForm {


    private String title;

    private Integer order;

    private LectureType type;
}
