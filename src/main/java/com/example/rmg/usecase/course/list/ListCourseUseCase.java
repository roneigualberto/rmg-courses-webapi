package com.example.rmg.usecase.course.list;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListCourseUseCase implements UseCase<ListCourseUseCaseInput, ListCourseUseCaseOutput> {

    private final CoursePersistence coursePersistence;

    @Override
    public ListCourseUseCaseOutput execute(ListCourseUseCaseInput input) {
        List<Course> courses = coursePersistence.findAll();
        return ListCourseUseCaseOutput.of(courses);
    }
}
