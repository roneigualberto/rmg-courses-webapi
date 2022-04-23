package com.example.rmg.usecase.course.lecture.list;


import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.usecase.course.lecture.common.mappers.LectureMapper;
import com.example.rmg.usecase.course.lecture.common.output.LectureView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Builder
public class ListLectureUseCaseOutput {

    private List<LectureView> lectures;

    public static ListLectureUseCaseOutput of(List<Lecture> lectures) {

        List<LectureView> lectureViews = lectures.stream().map(LectureMapper.INSTANCE::toLectureView).collect(Collectors.toList());

        return ListLectureUseCaseOutput.builder().lectures(lectureViews).build();
    }
}
