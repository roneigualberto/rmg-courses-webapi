package com.example.rmg.domain.course.valueobject;

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
public class LectureMedia {

    private String path;

    private Integer order;

    @NotNull
    private LectureType type;

    public void valid() {
        ValidatorUtil.validate(this);
    }
}
