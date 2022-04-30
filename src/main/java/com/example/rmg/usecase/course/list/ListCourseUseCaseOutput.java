package com.example.rmg.usecase.course.list;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.usecase.course.common.mappers.CourseMapper;
import com.example.rmg.usecase.course.common.output.CourseView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class ListCourseUseCaseOutput {

    List<CourseView> courses;

    public static ListCourseUseCaseOutput of(List<Course> courses) {
        List<CourseView> categoriesView = courses.stream()
                .map(CourseMapper.INSTANCE::toCourseView)
                .collect(Collectors.toList());

        return ListCourseUseCaseOutput.builder()
                .courses(categoriesView)
                .build();
    }
}
