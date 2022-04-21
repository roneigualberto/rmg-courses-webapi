package com.example.rmg.usecase.category.list;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.usecase.category.common.CategoryView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class ListCategoryUseCaseOutput {

    List<CategoryView> categories;

    public static ListCategoryUseCaseOutput of(List<Category> categories) {
        List<CategoryView> categoriesView = categories.stream().map(CategoryView::of).collect(Collectors.toList());

        return ListCategoryUseCaseOutput.builder()
                .categories(categoriesView)
                .build();
    }
}
