package com.example.rmg.usecase.course.update;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.usecase.course.common.mappers.CourseMapper;
import com.example.rmg.usecase.course.common.output.CourseView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class UpdateCourseUseCaseOutput {

    private CourseView course;


    public static UpdateCourseUseCaseOutput of(Course course) {

        return UpdateCourseUseCaseOutput.builder()
                .course(CourseMapper.INSTANCE.toCourseView(course))
                .build();

    }

}
