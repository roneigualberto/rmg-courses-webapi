package com.example.rmg.usecase.category.find;


import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class DefaultFindCategoryUseCase implements FindCategoryUseCase {


    private final CategoryPersistence categoryPersistence;


    @Override
    public FindCategoryUseCaseOutput execute(FindCategoryUseCaseInput input) {

        Category category = categoryPersistence.get(input.getCategoryId());

        return FindCategoryUseCaseOutput.of(category);
    }
}
