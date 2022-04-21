package com.example.rmg.usecase.category.create;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.usecase.category.common.ouput.CategoryView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class CreateCategoryUseCaseOutput {

    private CategoryView category;


    public static CreateCategoryUseCaseOutput of(Category category) {

        return CreateCategoryUseCaseOutput.builder()
                .category(CategoryView.of(category))
                .build();

    }

}
