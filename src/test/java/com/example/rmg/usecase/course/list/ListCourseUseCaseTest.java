package com.example.rmg.usecase.course.list;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.usecase.course.common.output.CourseView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.example.rmg.infrastructure.test.builders.Courses.aCourse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListCourseUseCaseTest {

    @InjectMocks
    private ListCourseUseCase useCase;


    @Mock
    private CoursePersistence coursePersistence;

    @Test
    void execute_should_list_courses() {
        ListCourseUseCaseInput input = ListCourseUseCaseInput.builder().build();

        List<Course> coursesMock = createCoursesMock();

        when(coursePersistence.findAll()).thenReturn(coursesMock);


        ListCourseUseCaseOutput output = useCase.execute(input);

        for (int i = 0; i < output.getCourses().size(); i++) {
            CourseView courseResult = output.getCourses().get(i);
            Course courseExpected = coursesMock.get(i);
            assertEquals(courseExpected.getName(), courseResult.getName());
            assertEquals(courseExpected.getTitle(), courseResult.getTitle());
            assertEquals(courseExpected.getDescription(), courseResult.getDescription());
        }

    }

    private List<Course> createCoursesMock() {
        List<Course> courses = new ArrayList<>();


        Course course1 = aCourse()
                .name("Course 1")
                .build();

        courses.add(course1);

        Course course2 = aCourse()
                .name("Course 2")
                .build();

        courses.add(course2);

        Course course3 = aCourse()
                .name("Course 3")
                .build();

        courses.add(course3);


        return courses;
    }
}