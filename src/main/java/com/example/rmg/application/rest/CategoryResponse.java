package com.example.rmg.application.rest;

import com.example.rmg.domain.category.valueobject.CategoryGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {


    private UUID id;

    private String name;

    private CategoryGroup group;
}
