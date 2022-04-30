package com.example.rmg.usecase.course.delete;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class DeleteCourseUseCase implements UseCase<DeleteCourseUseCaseInput, DeleteCourseUseCaseOutput> {


    private final CoursePersistence coursePersistence;

    @Override
    public DeleteCourseUseCaseOutput execute(DeleteCourseUseCaseInput input) {

        Course course = coursePersistence.get(input.getCourseId());

        coursePersistence.deleteById(course.getId());

        return DeleteCourseUseCaseOutput.builder().build();
    }
}
