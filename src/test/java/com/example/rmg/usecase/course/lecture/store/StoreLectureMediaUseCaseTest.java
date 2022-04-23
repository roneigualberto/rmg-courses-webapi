package com.example.rmg.usecase.course.lecture.store;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.domain.course.storage.StorageService;
import com.example.rmg.infrastructure.test.builders.Lectures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static com.example.rmg.infrastructure.test.builders.Courses.aCourse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StoreLectureMediaUseCaseTest {

    @InjectMocks
    private StoreLectureMediaUseCase useCase;

    @Spy
    private CoursePersistence coursePersistence;

    @Spy
    private LecturePersistence lecturePersistence;

    @Mock
    private StorageService storageService;

    @Test
    public void should_store_lecture() {
        final Course course = aCourse().build();
        when(coursePersistence.findById(any())).thenReturn(Optional.of(course));

        final Lecture lecture = Lectures.aLecture(course).build();
        when(lecturePersistence.findById(any())).thenReturn(Optional.of(lecture));

        final ByteArrayInputStream content = new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8));

        final StoreLectureMediaUseCaseInput input = StoreLectureMediaUseCaseInput.builder()
                .courseId(course.getId())
                .lectureId(lecture.getId())
                .content(content)
                .build();


        StoreLectureUseCaseOutput output = useCase.execute(input);

        assertNotNull(output);
        verify(storageService).store(any(), any(), any());

    }
}