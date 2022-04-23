package com.example.rmg.usecase.course.find;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.usecase.course.common.mappers.CourseMapper;
import com.example.rmg.usecase.course.common.output.CourseView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class FindCourseUseCaseOutput {

    private CourseView course;


    public static FindCourseUseCaseOutput of(Course course) {

        return FindCourseUseCaseOutput.builder()
                .course(CourseMapper.INSTANCE.toCourseView(course))
                .build();

    }


}
