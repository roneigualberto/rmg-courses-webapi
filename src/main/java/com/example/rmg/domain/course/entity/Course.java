package com.example.rmg.domain.course.entity;


import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.course.valueobject.SkillLevel;
import com.example.rmg.domain.common.validator.ValidatorUtil;
import com.example.rmg.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class Course {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotNull
    private User instructor;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Title is required")
    private String title;

    private String description;

    @NotNull
    private SkillLevel skillLevel;

    @NotNull
    private Double price;

    @NotNull
    private Category category;

    @NotNull
    @Builder.Default
    private Boolean published = Boolean.FALSE;

    private LocalDateTime publishDate;


    public void valid() {
        ValidatorUtil.validate(this);
    }


    public void publish() {
        this.published = Boolean.TRUE;
        this.publishDate = LocalDateTime.now();
    }

}
