package com.example.rmg.infrastructure.application.rest.category;

import com.example.rmg.usecase.category.common.ouput.CategoryView;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseInput;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCaseInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CreateCategoryUseCaseInput toCreateCategoryUseCaseInput(CategoryRequest request);

    @Mapping(target = "categoryId", ignore = true)
    UpdateCategoryUseCaseInput toUpdateCategoryUseCaseInput(CategoryRequest request);

    CategoryResponse toCategoryResponse(CategoryView categoryView);

    List<CategoryResponse> toCategoryResponseList(List<CategoryView> categoryViewList);

}
