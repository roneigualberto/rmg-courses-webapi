package com.example.rmg.usecase.course.lecture.common.mappers;


import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.usecase.course.lecture.common.output.LectureView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LectureMapper {


    LectureMapper INSTANCE = Mappers.getMapper(LectureMapper.class);

    @Mapping(target = "courseId", source = "course.id")
    LectureView toLectureView(Lecture lecture);
}
