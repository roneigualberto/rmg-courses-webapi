package com.example.rmg.domain.course.valueobject;

import com.example.rmg.domain.common.validator.ValidatorUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@Data
@Builder
public class LectureMedia {

    private String path;

    private Integer order;

    @NotNull
    private LectureType type;

    public void valid() {
        ValidatorUtil.validate(this);
    }
}
