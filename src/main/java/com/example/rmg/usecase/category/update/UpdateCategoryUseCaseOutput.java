package com.example.rmg.usecase.category.update;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.usecase.category.common.ouput.CategoryView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class UpdateCategoryUseCaseOutput {

    private CategoryView category;


    public static UpdateCategoryUseCaseOutput of(Category category) {

        return UpdateCategoryUseCaseOutput.builder()
                .category(CategoryView.of(category))
                .build();

    }

}
