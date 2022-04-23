package com.example.rmg.application.rest.course;

import com.example.rmg.domain.course.valueobject.LectureType;
import com.example.rmg.domain.course.valueobject.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class LectureRequest {

    private String title;

    private Integer order;

    private LectureType type;

}
