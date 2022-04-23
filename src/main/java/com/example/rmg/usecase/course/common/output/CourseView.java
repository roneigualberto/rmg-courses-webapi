package com.example.rmg.usecase.course.common.output;


import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.valueobject.SkillLevel;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.usecase.category.common.ouput.CategoryView;
import com.example.rmg.usecase.user.create.common.output.UserView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class CourseView {

    private UUID id;

    private String name;

    private UserView instructor;

    private String title;

    private String description;

    private SkillLevel skillLevel;

    private Double price;

    private CategoryView category;

    private Boolean published;

    private LocalDateTime publishDate;

}
