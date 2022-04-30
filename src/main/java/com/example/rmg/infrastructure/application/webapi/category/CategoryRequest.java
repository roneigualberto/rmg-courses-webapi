package com.example.rmg.infrastructure.application.webapi.category;

import com.example.rmg.domain.category.valueobject.CategoryGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Schema(name = "Category")
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
