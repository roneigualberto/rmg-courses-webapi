package com.example.rmg.usecase.category.update;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.messages.CategoryMessages;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class DefaultUpdateCategoryUseCase implements  UpdateCategoryUseCase {


    private final CategoryPersistence categoryPersistence;


    @Override
    public UpdateCategoryUseCaseOutput execute(UpdateCategoryUseCaseInput input) {

        validInput(input);

        Category category = categoryPersistence.get(input.getCategoryId());

        category.setName(input.getName());
        category.setGroup(input.getGroup());
        category.valid();

        categoryPersistence.update(category);

        return UpdateCategoryUseCaseOutput.of(category);
    }

    private void validInput(UpdateCategoryUseCaseInput input) {
        Optional<Category> optCategory = categoryPersistence.findByName(input.getName());

        if (optCategory.isPresent()) {
            boolean isSameCategory = Objects.equals(optCategory.get().getId(), input.getCategoryId());
            if (!isSameCategory) {
                throw new DomainException(CategoryMessages.CATEGORY_NAME_EXISTS);
            }
        }
    }
}
