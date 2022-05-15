package com.example.rmg.usecase.category.list;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DefaultListCategoryUseCase implements ListCategoryUseCase {


    private final CategoryPersistence categoryPersistence;

    @Override
    public ListCategoryUseCaseOutput execute(ListCategoryUseCaseInput input) {
        List<Category> categories = categoryPersistence.findAll();
        return ListCategoryUseCaseOutput.of(categories);
    }
}
