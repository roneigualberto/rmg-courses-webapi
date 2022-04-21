package com.example.rmg.usecase.category.create;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.valueobject.CategoryGroup;
import com.example.rmg.usecase.category.common.CategoryView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@AllArgsConstructor
@Builder
public class CreateCategoryOutput {

    private CategoryView category;


    public static CreateCategoryOutput of(Category category) {

        return CreateCategoryOutput.builder()
                .category(CategoryView.of(category))
                .build();

    }

}
