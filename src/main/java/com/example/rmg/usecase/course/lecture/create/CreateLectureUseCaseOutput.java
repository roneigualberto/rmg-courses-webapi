package com.example.rmg.usecase.course.lecture.create;


import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.usecase.course.lecture.common.input.LectureForm;
import com.example.rmg.usecase.course.lecture.common.mappers.LectureMapper;
import com.example.rmg.usecase.course.lecture.common.output.LectureView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CreateLectureUseCaseOutput {

    private LectureView lecture;

    public static CreateLectureUseCaseOutput of(Lecture lecture) {
       return  CreateLectureUseCaseOutput.builder().lecture(LectureMapper.INSTANCE.toLectureView(lecture)).build();
    }
}
