package com.example.rmg.usecase.course.create;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCourseUseCase implements UseCase<CreateCourseUseCaseInput, CreateCourseUseCaseOutput> {


    private final CoursePersistence coursePersistence;

    private final UserPersistence userPersistence;

    private final CategoryPersistence categoryPersistence;


    @Override
    public CreateCourseUseCaseOutput execute(CreateCourseUseCaseInput input) {


        User instructor = userPersistence.get(input.getInstructorId());
        Category category = categoryPersistence.get(input.getCategoryId());


        Course course = Course.builder()
                .name(input.getName())
                .instructor(instructor)
                .category(category)
                .skillLevel(input.getSkillLevel())
                .price(input.getPrice())
                .title(input.getTitle())
                .description(input.getDescription())
                .build();

        coursePersistence.save(course);

        return CreateCourseUseCaseOutput.of(course);
    }
}
