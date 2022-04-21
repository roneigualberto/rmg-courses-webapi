package com.example.rmg.usecase.category.update;

import com.example.rmg.domain.category.valueobject.CategoryGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UpdateCategoryUseCaseInput {


    private UUID categoryId;

    private String name;

    private CategoryGroup group;

}
