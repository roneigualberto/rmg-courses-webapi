package com.example.rmg.usecase.category.common;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.valueobject.CategoryGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@AllArgsConstructor
@Builder
public class CategoryView {


    private UUID id;

    private String name;

    private CategoryGroup group;


    public static CategoryView of(Category category) {
       return  CategoryView.builder()
                .id(category.getId())
                .name(category.getName())
                .group(category.getGroup())
                .build();
    }

}
