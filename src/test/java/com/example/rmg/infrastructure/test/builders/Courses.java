package com.example.rmg.infrastructure.test.builders;

import com.example.rmg.application.rest.course.CourseRequest;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.valueobject.SkillLevel;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntity;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntity;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import com.example.rmg.usecase.course.create.CreateCourseUseCaseInput;
import com.example.rmg.usecase.course.update.UpdateCourseUseCaseInput;

import java.util.UUID;

public class Courses {


    public static final String COURSE_NAME = "Course Name";
    public static final String COURSE_TITLE = "Course Title";
    public static final String COURSE_DESCRIPTION = "Course Description";
    public static final double COURSE_PRICE = 19.90;
    public static final SkillLevel COURSE_SKILL_LEVEL = SkillLevel.BEGINNER;

    public static CreateCourseUseCaseInput.CreateCourseUseCaseInputBuilder aCreateCourseUseCaseInput(UUID categoryId, UUID instructorId) {

        return CreateCourseUseCaseInput.builder()
                .categoryId(categoryId)
                .name(COURSE_NAME)
                .title(COURSE_TITLE)
                .description(COURSE_DESCRIPTION)
                .instructorId(instructorId)
                .skillLevel(COURSE_SKILL_LEVEL)
                .price(COURSE_PRICE);
    }


    public static CourseRequest.CourseRequestBuilder aCourseRequest(UUID categoryId, UUID instructorId) {

        return CourseRequest.builder()
                .categoryId(categoryId)
                .name(COURSE_NAME)
                .title(COURSE_TITLE)
                .description(COURSE_DESCRIPTION)
                .instructorId(instructorId)
                .skillLevel(COURSE_SKILL_LEVEL)
                .price(COURSE_PRICE);
    }


    public static Course.CourseBuilder aCourse() {

        return Course.builder()
                .category(Categories.aCategory().build())
                .name(COURSE_NAME)
                .title(COURSE_TITLE)
                .description(COURSE_DESCRIPTION)
                .instructor(Users.anUser().build())
                .skillLevel(COURSE_SKILL_LEVEL)
                .price(COURSE_PRICE);
    }

    public static CourseEntity.CourseEntityBuilder aCourseEntity(CategoryEntity category, UserEntity instructor) {

        return CourseEntity.builder()
                .id(UUID.randomUUID())
                .category(category)
                .name(COURSE_NAME)
                .title(COURSE_TITLE)
                .description(COURSE_DESCRIPTION)
                .instructor(instructor)
                .skillLevel(COURSE_SKILL_LEVEL)
                .price(COURSE_PRICE);
    }


    public static UpdateCourseUseCaseInput.UpdateCourseUseCaseInputBuilder aUpdateCourseUseCaseInput(UUID courseId) {
        return UpdateCourseUseCaseInput.builder()
                .courseId(courseId)
                .name("Course Name Updated")
                .title("Course Title Updated")
                .skillLevel(SkillLevel.ADVANCED)
                .price(20.20)
                .description("Description Updated");
    }


}
