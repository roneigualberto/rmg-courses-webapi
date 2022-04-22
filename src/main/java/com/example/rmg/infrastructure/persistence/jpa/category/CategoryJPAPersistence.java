package com.example.rmg.infrastructure.persistence.jpa.category;


import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryJPAPersistence implements CategoryPersistence {


    private final CategoryEntityRepository repository;

    private final CategoryEntityMapper categoryEntityMapper;


    @Override
    public void save(Category category) {
        CategoryEntity categoryEntity = categoryEntityMapper.toCategoryEntity(category);
        repository.save(categoryEntity);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return repository.findByName(name).map(categoryEntityMapper::toCategory);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll().stream().map(categoryEntityMapper::toCategory).collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findById(UUID categoryId) {
        return repository.findById(categoryId).map(categoryEntityMapper::toCategory);
    }

    @Override
    public void update(Category category) {
        this.save(category);
    }

    @Override
    public void deleteById(UUID categoryId) {
        this.repository.deleteById(categoryId);
    }
}
