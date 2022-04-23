package com.example.rmg.usecase.course.update;

import com.example.rmg.application.rest.course.CourseResponse;
import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.messages.CategoryMessages;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCaseInput;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCaseOutput;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class UpdateCourseUseCase implements UseCase<UpdateCourseUseCaseInput, UpdateCourseUseCaseOutput> {


    private final CoursePersistence coursePersistence;

    private final CategoryPersistence categoryPersistence;

    private final UserPersistence userPersistence;

    @Override
    public UpdateCourseUseCaseOutput execute(UpdateCourseUseCaseInput input) {

        User instructor = userPersistence.get(input.getInstructorId());
        Category category = categoryPersistence.get(input.getCategoryId());
        Course course = coursePersistence.get(input.getCourseId());

        course.setName(input.getName());
        course.setInstructor(instructor);
        course.setCategory(category);
        course.setDescription(input.getDescription());
        course.setPrice(input.getPrice());
        course.setSkillLevel(input.getSkillLevel());

        course.valid();

        coursePersistence.update(course);

        return UpdateCourseUseCaseOutput.of(course);
    }

}
