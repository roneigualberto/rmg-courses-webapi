package com.example.rmg.domain.course.persistence;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.common.persistence.GenericPersistence;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.messages.CourseMessages;

import java.util.UUID;

public interface CoursePersistence extends GenericPersistence<Course, UUID> {

    default Course get(UUID courseId) throws DomainException {
        return findById(courseId).orElseThrow(() -> new DomainException(CourseMessages.COURSE_NOT_FOUND));
    }
}
