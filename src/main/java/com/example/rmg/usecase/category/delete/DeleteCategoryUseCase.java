package com.example.rmg.usecase.category.delete;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.usecase.category.common.UseCase;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class DeleteCategoryUseCase implements UseCase<DeleteCategoryUseCaseInput, DeleteCategoryUseCaseOutput> {


    private final CategoryPersistence categoryPersistence;

    @Override
    public DeleteCategoryUseCaseOutput execute(DeleteCategoryUseCaseInput input) {

        Category category = categoryPersistence.get(input.getCategoryId());
        categoryPersistence.deleteById(category.getId());

        return DeleteCategoryUseCaseOutput.builder().build();
    }
}
