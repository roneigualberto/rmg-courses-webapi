package com.example.rmg.application.rest.category;

import com.example.rmg.domain.category.valueobject.CategoryGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CategoryRequest {


    @NotBlank
    private String name;

    @NotNull
    private CategoryGroup group;
}
