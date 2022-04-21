package com.example.rmg.infrastructure.persistence.jpa.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, UUID> {


    Optional<CategoryEntity> findByName(String name);
}
