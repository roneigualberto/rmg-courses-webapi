package com.example.rmg.usecase.category.list;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.usecase.category.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ListCategoryUseCase implements UseCase<ListCategoryUseCaseInput, ListCategoryUseCaseOutput> {


    private final CategoryPersistence categoryPersistence;

    @Override
    public ListCategoryUseCaseOutput execute(ListCategoryUseCaseInput input) {


        List<Category> categories = categoryPersistence.findAll();

        List<CategoryView> categoriesView = categories.stream().map((category) ->
                CategoryView.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .group(category.getGroup())
                        .build()
        ).collect(Collectors.toList());

        return ListCategoryUseCaseOutput.builder().categories(categoriesView).build();
    }
}
