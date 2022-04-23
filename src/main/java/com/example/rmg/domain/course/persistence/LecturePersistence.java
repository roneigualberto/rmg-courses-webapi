package com.example.rmg.domain.course.persistence;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.common.persistence.GenericPersistence;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.messages.CourseMessages;

import java.util.UUID;

public interface LecturePersistence extends GenericPersistence<Lecture, UUID> {


    boolean existsWithOrder(Course course, Integer order);

    default Lecture get(UUID lectureId) throws DomainException {
        return findById(lectureId).orElseThrow(() -> new DomainException(CourseMessages.LECTURE_NOT_FOUND));
    }
}
