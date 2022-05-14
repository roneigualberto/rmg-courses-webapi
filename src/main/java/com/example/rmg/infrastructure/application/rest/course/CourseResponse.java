package com.example.rmg.infrastructure.application.rest.course;

import com.example.rmg.infrastructure.application.rest.category.CategoryResponse;
import com.example.rmg.infrastructure.application.rest.user.UserResponse;
import com.example.rmg.domain.course.valueobject.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CourseResponse {

    private UUID id;

    private String name;

    private UserResponse instructor;

    private CategoryResponse category;

    private String title;

    private String description;

    private SkillLevel skillLevel;

    private Double price;

    private Boolean published;

    private LocalDateTime publishDate;
}
