package com.example.rmg.domain.category.persistence;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.messages.CategoryMessages;
import com.example.rmg.domain.category.shared.DomainException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryPersistence {

    void save(Category category);


    Optional<Category> findByName(String name);

    List<Category> findAll();

    Optional<Category> findById(UUID categoryId);

    void update(Category category);


    default Category get(UUID categoryId) throws DomainException {
        return findById(categoryId).orElseThrow(() -> new DomainException(CategoryMessages.CATEGORY_NOT_FOUND));
    }
}
