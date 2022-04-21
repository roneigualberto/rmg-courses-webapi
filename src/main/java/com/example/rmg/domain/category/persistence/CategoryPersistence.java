package com.example.rmg.domain.category.persistence;

import com.example.rmg.domain.category.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryPersistence {

    void save(Category category);


    Optional<Category> findByName(String name);

    List<Category> findAll();
}
