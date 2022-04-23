package com.example.rmg.infrastructure.test.builders;

import com.example.rmg.application.rest.course.CourseRequest;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.valueobject.LectureType;
import com.example.rmg.domain.course.valueobject.SkillLevel;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntity;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntity;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import com.example.rmg.usecase.course.create.CreateCourseUseCaseInput;
import com.example.rmg.usecase.course.lecture.common.input.LectureForm;
import com.example.rmg.usecase.course.lecture.create.CreateLectureUseCaseInput;
import com.example.rmg.usecase.course.update.UpdateCourseUseCaseInput;

import java.util.UUID;

public class Lectures {


    public static LectureForm.LectureFormBuilder aLectureForm() {
        return LectureForm.builder()
                .title("Lecture Title")
                .order(1)
                .type(LectureType.VIDEO);
    }

}
