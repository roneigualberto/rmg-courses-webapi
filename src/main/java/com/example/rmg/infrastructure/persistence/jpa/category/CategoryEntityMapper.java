package com.example.rmg.infrastructure.persistence.jpa.category;


import com.example.rmg.domain.category.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {


    CategoryEntity toCategoryEntity(Category category);

    Category toCategory(CategoryEntity entity);
}
