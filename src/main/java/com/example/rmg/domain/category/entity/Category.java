package com.example.rmg.domain.category.entity;

import com.example.rmg.domain.category.valueobject.CategoryGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@Getter
@Builder
@AllArgsConstructor
public class Category {

    private UUID id;

    private String name;

    private CategoryGroup group;
}
