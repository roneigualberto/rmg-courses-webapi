package com.example.rmg.usecase.course.create;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.usecase.course.common.mappers.CourseMapper;
import com.example.rmg.usecase.course.common.output.CourseView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class CreateCourseUseCaseOutput {

    private CourseView course;


    public static CreateCourseUseCaseOutput of(Course course) {

        return CreateCourseUseCaseOutput.builder()
                .course(CourseMapper.INSTANCE.toCourseView(course))
                .build();

    }

}
