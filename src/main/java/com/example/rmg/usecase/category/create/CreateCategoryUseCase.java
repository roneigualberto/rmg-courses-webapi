package com.example.rmg.usecase.category.create;


import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.category.shared.DomainException;
import com.example.rmg.usecase.category.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
public class CreateCategoryUseCase implements UseCase<CreateCategoryInput, CreateCategoryOutput> {


    private final CategoryPersistence categoryPersistence;

    @Override
    public CreateCategoryOutput execute(CreateCategoryInput input) {


        Optional<Category> optCategory = categoryPersistence.findByName(input.getName());

        if (optCategory.isPresent()) {
            throw new DomainException("Category name already exists");
        }

        Category category = Category.builder()
                .id(UUID.randomUUID())
                .name(input.getName())
                .group(input.getGroup())
                .build();

        categoryPersistence.save(category);


        return CreateCategoryOutput.builder()
                .id(category.getId())
                .name(category.getName())
                .group(category.getGroup())
                .build();

    }
}
