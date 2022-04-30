package com.example.rmg.usecase.course.lecture.list;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.usecase.course.lecture.common.output.LectureView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.rmg.infrastructure.test.builders.Courses.aCourse;
import static com.example.rmg.infrastructure.test.builders.Lectures.aLecture;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ListLectureUseCaseTest {


    @InjectMocks
    private ListLectureUseCase useCase;

    @Spy
    private CoursePersistence coursePersistence;

    @Spy
    private LecturePersistence lecturePersistence;

    @Test
    void should_list_lectures() {
        Course course = aCourse().build();

        when(coursePersistence.findById(any())).thenReturn(Optional.of(course));

        Lecture lecture1 = aLecture(course).title("Lecture 1").build();
        Lecture lecture2 = aLecture(course).title("Lecture 2").build();
        Lecture lecture3 = aLecture(course).title("Lecture 3").build();

        List<Lecture> lectures = List.of(lecture1, lecture2, lecture3);

        when(lecturePersistence.findByCourse(any())).thenReturn(lectures);

        ListLectureUseCaseInput input = ListLectureUseCaseInput.builder().courseId(course.getId()).build();

        ListLectureUseCaseOutput output = useCase.execute(input);

        for (int i = 0; i < output.getLectures().size(); i++) {
            Lecture lectureExpected = lectures.get(i);
            LectureView lectureResult = output.getLectures().get(i);
            assertEquals(lectureExpected.getId(), lectureResult.getId());
            assertEquals(lectureExpected.getTitle(), lectureResult.getTitle());
            assertEquals(lectureExpected.getOrder(), lectureResult.getOrder());
            assertEquals(lectureExpected.getType(), lectureResult.getType());
        }
    }


}