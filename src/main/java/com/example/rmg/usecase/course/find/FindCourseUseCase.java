package com.example.rmg.usecase.course.find;


import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class FindCourseUseCase implements UseCase<FindCourseUseCaseInput, FindCourseUseCaseOutput> {


    private final CoursePersistence coursePersistence;

    @Override
    public FindCourseUseCaseOutput execute(FindCourseUseCaseInput input) {

        Course course = coursePersistence.get(input.getCourseId());

        return FindCourseUseCaseOutput.of(course);
    }
}
