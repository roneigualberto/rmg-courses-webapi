package com.example.rmg.application.rest.course;

import com.example.rmg.usecase.course.common.output.CourseView;
import com.example.rmg.usecase.course.create.CreateCourseUseCaseInput;
import com.example.rmg.usecase.course.lecture.common.input.LectureForm;
import com.example.rmg.usecase.course.lecture.common.output.LectureView;
import com.example.rmg.usecase.course.update.UpdateCourseUseCaseInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CreateCourseUseCaseInput toCreateCourseUseCaseInput(CourseRequest request);

    CourseResponse toCourseResponse(CourseView course);

    List<CourseResponse> toCourseResponseList(List<CourseView> courseViewList);

    @Mapping(target = "courseId", ignore = true)
    UpdateCourseUseCaseInput toUpdateCourseUseCaseInput(CourseRequest request);


    LectureForm toLectureForm(LectureRequest request);

    LectureResponse toLectureResponse(LectureView lectureView);

    List<LectureResponse> toLectureResponseList(List<LectureView> lectureViewList);


}
