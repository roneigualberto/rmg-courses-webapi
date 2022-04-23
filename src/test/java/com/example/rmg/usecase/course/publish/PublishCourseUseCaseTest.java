package com.example.rmg.usecase.course.publish;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.infrastructure.test.builders.Courses;
import com.example.rmg.usecase.course.common.output.CourseView;
import com.example.rmg.usecase.course.find.FindCourseUseCaseInput;
import com.example.rmg.usecase.course.find.FindCourseUseCaseOutput;
import com.example.rmg.usecase.course.list.ListCourseUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PublishCourseUseCaseTest {

    @InjectMocks
    private PublishCourseUseCase useCase;

    @Spy
    private CoursePersistence coursePersistence;


    @Test
    void execute_should_publish_course() {

        PublishCourseUseCaseInput input = PublishCourseUseCaseInput.builder()
                .courseId(UUID.randomUUID())
                .build();

        Course course = Courses.aCourse().build();

        when(coursePersistence.findById(any())).thenReturn(Optional.of(course));

        PublishCourseUseCaseOutput output = useCase.execute(input);

        verify(coursePersistence).update(any());


    }
}