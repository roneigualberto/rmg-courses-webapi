package com.example.rmg.application.rest.course;

import com.example.rmg.application.rest.category.CategoryRequest;
import com.example.rmg.application.rest.category.CategoryResponse;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.usecase.category.common.ouput.CategoryView;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseInput;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCaseInput;
import com.example.rmg.usecase.course.common.output.CourseView;
import com.example.rmg.usecase.course.create.CreateCourseUseCaseInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CreateCourseUseCaseInput toCreateCourseUseCaseInput(CourseRequest request);

    CourseResponse toCourseResponse(CourseView course);

    List<CourseResponse> toCourseResponseList(List<CourseView> courseViewList);

}
