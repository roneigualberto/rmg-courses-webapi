package com.example.rmg.infrastructure.persistence.jpa.course;


import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseJPAPersistence implements CoursePersistence {


    private final CourseEntityRepository repository;

    private final CourseEntityMapper entityMapper;


    @Override
    public void save(Course course) {
        CourseEntity entity = entityMapper.toCourseEntity(course);
        repository.save(entity);
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll().stream().map(entityMapper::toCourse).collect(Collectors.toList());
    }

    @Override
    public Optional<Course> findById(UUID courseId) {
        return repository.findById(courseId).map(entityMapper::toCourse);
    }

    @Override
    public void update(Course course) {
        this.save(course);
    }

    @Override
    public void deleteById(UUID courseId) {
        this.repository.deleteById(courseId);
    }
}
