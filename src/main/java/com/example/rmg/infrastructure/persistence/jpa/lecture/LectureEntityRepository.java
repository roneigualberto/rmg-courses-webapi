package com.example.rmg.infrastructure.persistence.jpa.lecture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface LectureEntityRepository extends JpaRepository<LectureEntity, UUID> {


    boolean existsByCourseIdAndOrder(UUID courseId, Integer order);
}
