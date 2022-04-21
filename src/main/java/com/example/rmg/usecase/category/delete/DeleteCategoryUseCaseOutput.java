package com.example.rmg.usecase.category.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class DeleteCategoryUseCaseOutput {

    private UUID categoryId;

}
