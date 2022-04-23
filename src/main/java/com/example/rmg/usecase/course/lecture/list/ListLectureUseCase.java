package com.example.rmg.usecase.course.lecture.list;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListLectureUseCase implements UseCase<ListLectureUseCaseInput, ListLectureUseCaseOutput> {


    private final CoursePersistence coursePersistence;

    private final LecturePersistence lecturePersistence;


    @Override
    public ListLectureUseCaseOutput execute(ListLectureUseCaseInput input) {

        Course course = coursePersistence.get(input.getCourseId());

        List<Lecture> lectures = lecturePersistence.findByCourse(course);

        return ListLectureUseCaseOutput.of(lectures);
    }
}
