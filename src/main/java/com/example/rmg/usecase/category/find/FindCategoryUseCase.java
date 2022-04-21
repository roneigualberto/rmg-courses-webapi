package com.example.rmg.usecase.category.find;


import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.messages.CategoryMessages;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.category.shared.DomainException;
import com.example.rmg.usecase.category.UseCase;
import com.example.rmg.usecase.category.create.CreateCategoryInput;
import com.example.rmg.usecase.category.create.CreateCategoryOutput;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

import static com.example.rmg.domain.category.messages.CategoryMessages.*;


@RequiredArgsConstructor
public class FindCategoryUseCase implements UseCase<FindCategoryUseCaseInput, FindCategoryUseCaseOutput> {


    private final CategoryPersistence categoryPersistence;


    @Override
    public FindCategoryUseCaseOutput execute(FindCategoryUseCaseInput input) {

        Category category = categoryPersistence.findById(input.getCategoryId()).orElseThrow(() -> new DomainException(CATEGORY_NOT_FOUND));

        return FindCategoryUseCaseOutput.of(category);
    }
}
