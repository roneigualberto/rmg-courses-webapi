package com.example.rmg.infrastructure.test.builders;

import com.example.rmg.application.rest.category.CategoryRequest;
import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.valueobject.CategoryGroup;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntity;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseInput;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCaseInput;
import com.example.rmg.usecase.course.create.CreateCourseUseCaseInput;

import java.util.UUID;

import static com.example.rmg.domain.category.valueobject.CategoryGroup.BUSINESS;

public class Categories {

    public static final String CATEGORY_NAME = "Category Name";
    public static final CategoryGroup CATEGORY_GROUP = CategoryGroup.DEVELOPMENT;


    public static Category.CategoryBuilder aCategory() {

        return Category.builder()
                .name(CATEGORY_NAME)
                .group(CATEGORY_GROUP);

    }

    public static CategoryRequest.CategoryRequestBuilder aCategoryRequest() {

        return CategoryRequest.builder()
                .name(CATEGORY_NAME)
                .group(CATEGORY_GROUP);

    }

    public static CategoryEntity.CategoryEntityBuilder aCategoryEntity() {

        return CategoryEntity.builder()
                .id(UUID.randomUUID())
                .name(CATEGORY_NAME)
                .group(CATEGORY_GROUP);

    }

    public static CreateCategoryUseCaseInput.CreateCategoryUseCaseInputBuilder aCreateCategoryUseCaseInput() {
        return CreateCategoryUseCaseInput.builder()
                .name(CATEGORY_NAME)
                .group(CATEGORY_GROUP);
    }

    public static UpdateCategoryUseCaseInput.UpdateCategoryUseCaseInputBuilder aCreateCategoryUseCaseInput(UUID categoryId) {
        return UpdateCategoryUseCaseInput.builder()
                .categoryId(categoryId)
                .name("Category Updated")
                .group(BUSINESS);
    }

}
