package com.example.rmg.infrastructure.test.builders;

import com.example.rmg.application.rest.course.CourseRequest;
import com.example.rmg.domain.course.valueobject.SkillLevel;
import com.example.rmg.usecase.course.create.CreateCourseUseCaseInput;

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


}
