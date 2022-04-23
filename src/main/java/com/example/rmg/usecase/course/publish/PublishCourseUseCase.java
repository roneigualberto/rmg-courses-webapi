package com.example.rmg.usecase.course.publish;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PublishCourseUseCase implements UseCase<PublishCourseUseCaseInput, PublishCourseUseCaseOutput> {

    private final CoursePersistence coursePersistence;

    @Override
    public PublishCourseUseCaseOutput execute(PublishCourseUseCaseInput input) {

        Course course = coursePersistence.get(input.getCourseId());

        course.publish();

        coursePersistence.update(course);

        return PublishCourseUseCaseOutput.builder().build();
    }
}
