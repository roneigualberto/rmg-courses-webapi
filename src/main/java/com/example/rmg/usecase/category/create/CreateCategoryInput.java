package com.example.rmg.usecase.category.create;

import com.example.rmg.domain.category.valueobject.CategoryGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateCategoryInput {


    private String name;

    private CategoryGroup group;

}
