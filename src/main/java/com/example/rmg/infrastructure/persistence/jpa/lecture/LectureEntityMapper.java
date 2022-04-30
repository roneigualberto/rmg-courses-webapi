package com.example.rmg.infrastructure.persistence.jpa.lecture;


import com.example.rmg.domain.course.entity.Lecture;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LectureEntityMapper {


    LectureEntity toLectureEntity(Lecture lecture);

    Lecture toLecture(LectureEntity entity);
}
