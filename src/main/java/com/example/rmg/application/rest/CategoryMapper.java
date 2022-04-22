package com.example.rmg.application.rest;

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

    CategoryResponse toCategoryRequest(CategoryView categoryView);

    List<CategoryResponse> toCategoryRequestList(List<CategoryView> categoryViewList);

}
