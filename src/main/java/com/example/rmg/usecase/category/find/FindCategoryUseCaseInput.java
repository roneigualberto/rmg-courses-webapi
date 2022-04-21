package com.example.rmg.usecase.category.find;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class FindCategoryUseCaseInput {

    private UUID categoryId;

}
