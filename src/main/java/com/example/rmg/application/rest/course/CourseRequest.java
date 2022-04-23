package com.example.rmg.application.rest.course;

import com.example.rmg.domain.course.valueobject.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CourseRequest {

    private String name;

    private UUID instructorId;

    private UUID categoryId;

    private String title;

    private String description;

    private SkillLevel skillLevel;

    private Double price;


}
