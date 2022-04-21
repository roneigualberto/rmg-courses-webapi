package com.example.rmg.usecase.category.create;


import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.category.shared.DomainException;
import com.example.rmg.usecase.category.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

import static com.example.rmg.domain.category.messages.CategoryMessages.*;


@RequiredArgsConstructor
public class CreateCategoryUseCase implements UseCase<CreateCategoryInput, CreateCategoryOutput> {


    private final CategoryPersistence categoryPersistence;

    @Override
    public CreateCategoryOutput execute(CreateCategoryInput input) {

        validateInput(input);

        Category category = Category.builder()
                .id(UUID.randomUUID())
                .name(input.getName())
                .group(input.getGroup())
                .build();

        categoryPersistence.save(category);

        return CreateCategoryOutput.of(category);

    }

    private void validateInput(CreateCategoryInput input) {

        Optional<Category> optCategory = categoryPersistence.findByName(input.getName());

        if (input.getName() == null) {
            throw new DomainException(CATEGORY_NAME_REQUIRED);
        }

        if (input.getGroup() == null) {
            throw new DomainException(CATEGORY_GROUP_REQUIRED);
        }

        if (optCategory.isPresent()) {
            throw new DomainException(CATEGORY_NAME_EXISTS);
        }
    }
}
