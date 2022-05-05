package com.example.rmg.infrastructure.application.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.rmg.usecase.course.common.output.CourseView;
import com.example.rmg.usecase.course.lecture.common.output.LectureView;
import com.example.rmg.usecase.course.lecture.list.ListLectureUseCase;
import com.example.rmg.usecase.course.lecture.list.ListLectureUseCaseInput;
import com.example.rmg.usecase.course.lecture.list.ListLectureUseCaseOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseResolver implements GraphQLResolver<CourseView> {

    private final ListLectureUseCase listLectureUseCase;


    public List<LectureView> getLectures(CourseView courseView) {

        ListLectureUseCaseInput input = ListLectureUseCaseInput.builder().courseId(courseView.getId()).build();

        ListLectureUseCaseOutput output = listLectureUseCase.execute(input);
        return output.getLectures();
    }

}
