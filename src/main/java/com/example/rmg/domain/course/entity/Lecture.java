package com.example.rmg.domain.course.entity;

import com.example.rmg.domain.common.validator.ValidatorUtil;
import com.example.rmg.domain.course.valueobject.LectureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@AllArgsConstructor
@Data
@Builder
public class Lecture {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotNull
    private Course course;

    @NotEmpty(message = "Title is required")
    private String title;

    @NotNull
    private Integer order;

    @NotNull
    private LectureType type;

    public void valid() {
        ValidatorUtil.validate(this);
    }
}
