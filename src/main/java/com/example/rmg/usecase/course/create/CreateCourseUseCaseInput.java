package com.example.rmg.usecase.course.create;

import com.example.rmg.domain.course.valueobject.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
public class CreateCourseUseCaseInput {

    private String name;

    private UUID instructorId;

    private String title;

    private String description;

    private SkillLevel skillLevel;

    private Double price;

    private UUID categoryId;

}
