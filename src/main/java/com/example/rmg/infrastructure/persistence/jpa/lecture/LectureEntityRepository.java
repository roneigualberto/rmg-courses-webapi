package com.example.rmg.infrastructure.persistence.jpa.lecture;

import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface LectureEntityRepository extends JpaRepository<LectureEntity, UUID> {


    boolean existsByCourseIdAndOrder(UUID courseId, Integer order);

    List<LectureEntity> findByCourse(CourseEntity course);

}
