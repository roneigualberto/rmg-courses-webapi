package com.example.rmg.usecase.course.common.mappers;


import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.usecase.course.common.output.CourseView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {


    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseView toCourseView(Course course);
}
