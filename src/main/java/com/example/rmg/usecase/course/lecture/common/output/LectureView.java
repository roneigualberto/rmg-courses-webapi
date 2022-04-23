package com.example.rmg.usecase.course.lecture.common.output;

import com.example.rmg.domain.course.valueobject.LectureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@AllArgsConstructor
@Data
@Builder
public class LectureView {

    private UUID id;

    private UUID courseId;

    private String title;

    private Integer order;

    private LectureType type;
}
