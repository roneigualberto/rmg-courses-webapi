package com.example.rmg.domain.category.entity;

import com.example.rmg.domain.category.valueobject.CategoryGroup;
import com.example.rmg.domain.common.validator.ValidatorUtil;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.example.rmg.domain.category.messages.CategoryMessages.CATEGORY_GROUP_REQUIRED;
import static com.example.rmg.domain.category.messages.CategoryMessages.CATEGORY_NAME_REQUIRED;

@Data
@Getter
@Builder
public class Category {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotEmpty(message = CATEGORY_NAME_REQUIRED)
    private String name;

    @NotNull(message = CATEGORY_GROUP_REQUIRED)
    private CategoryGroup group;

    public void valid() {
        ValidatorUtil.validate(this);
    }
}
