package com.example.rmg.infrastructure.persistence.jpa.course;


import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseEntityMapper {


    CourseEntity toCourseEntity(Course course);

    Course toCourse(CourseEntity entity);
}
