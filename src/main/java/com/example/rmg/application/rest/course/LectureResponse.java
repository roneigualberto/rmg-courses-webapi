package com.example.rmg.application.rest.course;

import com.example.rmg.domain.course.valueobject.LectureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class LectureResponse {


    private UUID id;

    private UUID courseId;

    private String title;

    private Integer order;

    private LectureType type;

}
