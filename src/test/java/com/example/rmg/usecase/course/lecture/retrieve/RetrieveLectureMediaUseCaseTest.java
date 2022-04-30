package com.example.rmg.usecase.course.lecture.retrieve;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.domain.course.storage.StorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static com.example.rmg.infrastructure.test.builders.Courses.aCourse;
import static com.example.rmg.infrastructure.test.builders.Lectures.aLecture;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RetrieveLectureMediaUseCaseTest {

    public static final String LECTURE_MEDIA_CONTENT = "<p>Lecture Content</p>";

    @InjectMocks
    private RetrieveLectureMediaUseCase useCase;

    @Mock
    private CoursePersistence coursePersistence;

    @Mock
    private LecturePersistence lecturePersistence;

    @Mock
    private StorageService storageService;


    @Test
    public void should_retrieve_lecture_media() {
        final Course course = aCourse().build();
        when(coursePersistence.get(any())).thenReturn(course);

        final Lecture lecture = aLecture(course).build();
        when(lecturePersistence.get(any())).thenReturn(lecture);
        final ByteArrayInputStream media = new ByteArrayInputStream(LECTURE_MEDIA_CONTENT.getBytes(StandardCharsets.UTF_8));

        when(storageService.get(any(), any())).thenReturn(media);

        final RetrieveLectureMediaUseCaseInput input = RetrieveLectureMediaUseCaseInput.builder()
                .courseId(course.getId())
                .lectureId(lecture.getId())
                .build();

        final RetrieveLectureMediaUseCaseOutput output = useCase.execute(input);

        assertEquals(media, output.getMedia());


    }

}