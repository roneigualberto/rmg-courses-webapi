package com.example.rmg.usecase.category.list;

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

}
