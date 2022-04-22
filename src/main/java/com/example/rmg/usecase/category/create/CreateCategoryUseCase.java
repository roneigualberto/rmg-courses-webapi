package com.example.rmg.usecase.category.create;


import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

import static com.example.rmg.domain.category.messages.CategoryMessages.*;


@RequiredArgsConstructor
public class CreateCategoryUseCase implements UseCase<CreateCategoryUseCaseInput, CreateCategoryUseCaseOutput> {


    private final CategoryPersistence categoryPersistence;

    @Override
    public CreateCategoryUseCaseOutput execute(CreateCategoryUseCaseInput input) {

        validateInput(input);

        Category category = Category.builder()
                .id(UUID.randomUUID())
                .name(input.getName())
                .group(input.getGroup())
                .build();

        category.valid(); // Valid Category

        categoryPersistence.save(category);

        return CreateCategoryUseCaseOutput.of(category);

    }

    private void validateInput(CreateCategoryUseCaseInput input) {

        Optional<Category> optCategory = categoryPersistence.findByName(input.getName());

        if (optCategory.isPresent()) {
            throw new DomainException(CATEGORY_NAME_EXISTS);
        }
    }
}
