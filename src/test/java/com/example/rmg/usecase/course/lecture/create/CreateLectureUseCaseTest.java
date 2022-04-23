package com.example.rmg.usecase.course.lecture.create;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.infrastructure.test.builders.Lectures;
import com.example.rmg.usecase.course.lecture.common.input.LectureForm;
import com.example.rmg.usecase.course.lecture.common.output.LectureView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.rmg.domain.course.messages.CourseMessages.LECTURE_ORDER_EXISTS;
import static com.example.rmg.infrastructure.test.builders.Courses.aCourse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateLectureUseCaseTest {


    @InjectMocks
    private CreateLectureUseCase useCase;

    @Spy
    private CoursePersistence coursePersistence;

    @Spy
    private LecturePersistence lecturePersistence;


    @Test
    void should_create_lecture() {

        Course course = aCourse().build();

        when(coursePersistence.findById(any())).thenReturn(Optional.of(course));

        LectureForm form = Lectures.aLectureForm().build();

        CreateLectureUseCaseInput input = CreateLectureUseCaseInput.builder()
                .courseId(course.getId())
                .lecture(form)
                .build();

        CreateLectureUseCaseOutput output = useCase.execute(input);

        LectureView lectureView = output.getLecture();

        assertNotNull(lectureView.getId());
        assertEquals(form.getTitle(), lectureView.getTitle());
        assertEquals(form.getOrder(), lectureView.getOrder());
        assertEquals(form.getType(), lectureView.getType());
        assertEquals(course.getId(), lectureView.getCourseId());

        verify(lecturePersistence).save(any());
    }

    @Test
    void should_not_create_lecture_when_order_already_exists() {

        Course course = aCourse().build();

        when(coursePersistence.findById(any())).thenReturn(Optional.of(course));

        LectureForm form = Lectures.aLectureForm().build();

        CreateLectureUseCaseInput input = CreateLectureUseCaseInput.builder()
                .courseId(course.getId())
                .lecture(form)
                .build();

        when(lecturePersistence.existsWithOrder(course,form.getOrder())).thenReturn(true);

        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));


        assertEquals(LECTURE_ORDER_EXISTS,exc.getMessage());

    }
}