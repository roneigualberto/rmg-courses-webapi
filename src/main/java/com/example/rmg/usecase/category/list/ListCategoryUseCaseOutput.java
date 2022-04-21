package com.example.rmg.usecase.category.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ListCategoryUseCaseOutput {

    List<CategoryView> categories;

}
