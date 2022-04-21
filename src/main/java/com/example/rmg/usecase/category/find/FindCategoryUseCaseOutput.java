package com.example.rmg.usecase.category.find;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.usecase.category.common.CategoryView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class FindCategoryUseCaseOutput {

    private CategoryView category;


    public static FindCategoryUseCaseOutput of(Category category) {

        return FindCategoryUseCaseOutput.builder()
                .category(CategoryView.of(category))
                .build();

    }

   
}
