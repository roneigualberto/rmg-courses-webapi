package com.example.rmg.infrastructure.persistence.jpa.course;

import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface CourseEntityRepository extends JpaRepository<CourseEntity, UUID> {
}
