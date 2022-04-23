package com.example.rmg.infrastructure.persistence.jpa.lecture;


import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntity;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntityMapper;
import com.example.rmg.infrastructure.persistence.jpa.lecture.LectureEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LectureJPAPersistence implements LecturePersistence {


    private final LectureEntityRepository repository;

    private final LectureEntityMapper entityMapper;

    @Override
    public void save(Lecture lecture) {
        LectureEntity entity = entityMapper.toLectureEntity(lecture);
        repository.save(entity);
    }

    @Override
    public void update(Lecture lecture) {
        this.save(lecture);
    }

    @Override
    public List<Lecture> findAll() {
        return repository.findAll().stream().map(entityMapper::toLecture).collect(Collectors.toList());
    }

    @Override
    public Optional<Lecture> findById(UUID lectureId) {
        return repository.findById(lectureId).map(entityMapper::toLecture);
    }

    @Override
    public void deleteById(UUID lectureId) {
        repository.deleteById(lectureId);
    }

    @Override
    public boolean existsWithOrder(UUID courseId, Integer order) {
        return repository.existsByCourseIdAndOrder(courseId, order);
    }

    @Override
    public List<Lecture> findByCourse(Course course) {

        CourseEntity courseEntity = CourseEntity.builder()
                .id(course.getId())
                .build();

        return repository.findByCourse(courseEntity)
                .stream().map(entityMapper::toLecture).collect(Collectors.toList());
    }
}


