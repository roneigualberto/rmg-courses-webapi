package com.example.rmg.domain.category.persistence;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.messages.CategoryMessages;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.common.persistence.GenericPersistence;

import java.util.Optional;
import java.util.UUID;

public interface CategoryPersistence extends GenericPersistence<Category, UUID> {

    public Optional<Category> findByName(String name);

    default Category get(UUID categoryId) throws DomainException {
        return findById(categoryId).orElseThrow(() -> new DomainException(CategoryMessages.CATEGORY_NOT_FOUND));
    }

}
