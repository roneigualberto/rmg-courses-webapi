package com.example.rmg.infrastructure.application.rest.course;

import com.example.rmg.domain.course.valueobject.LectureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LectureRequest {

    private String title;

    private Integer order;

    private LectureType type;

}
