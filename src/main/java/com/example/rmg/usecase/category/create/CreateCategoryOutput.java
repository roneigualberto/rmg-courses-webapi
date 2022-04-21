package com.example.rmg.usecase.category.create;

import com.example.rmg.domain.category.valueobject.CategoryGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@AllArgsConstructor
@Builder
public class CreateCategoryOutput {


    private UUID id;

    private String name;

    private CategoryGroup group;
}
