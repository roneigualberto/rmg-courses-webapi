package com.example.rmg.usecase.course.find;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.infrastructure.test.builders.Courses;
import com.example.rmg.usecase.course.common.output.CourseView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.example.rmg.domain.course.messages.CourseMessages.COURSE_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FindCourseUseCaseTest {


    @InjectMocks
    private FindCourseUseCase useCase;

    @Spy
    private CoursePersistence coursePersistence;

    @Test
    void execute_should_find_course_by_id() {

        FindCourseUseCaseInput input = FindCourseUseCaseInput.builder()
                .courseId(UUID.randomUUID())
                .build();

        Course course = Courses.aCourse().build();

        when(coursePersistence.findById(any())).thenReturn(Optional.of(course));

        FindCourseUseCaseOutput output = useCase.execute(input);

        CourseView courseView = output.getCourse();


        assertEquals(courseView.getName(), course.getName());
        assertEquals(courseView.getTitle(), course.getTitle());
        assertEquals(courseView.getId(), course.getId());

    }

    @Test
    void execute_should_not_find_course_by_id() {
        FindCourseUseCaseInput input = FindCourseUseCaseInput.builder()
                .courseId(UUID.randomUUID())
                .build();

        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));

        assertEquals(exc.getMessage(), COURSE_NOT_FOUND);

    }

}