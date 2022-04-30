package com.example.rmg.usecase.subscription.create;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.subscription.persistence.SubscriptionPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.infrastructure.test.builders.Courses;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.rmg.infrastructure.test.builders.Users.anUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateSubscriptionUseCaseTest {


    @InjectMocks
    private CreateSubscriptionUseCase useCase;

    @Mock
    private SubscriptionPersistence subscriptionPersistence;

    @Mock
    private UserPersistence userPersistence;

    @Mock
    private CoursePersistence coursePersistence;

    @Test
    void should_create_subscription() {

        final User student = givenStudent();
        final List<Course> courses = givenCourses();
        final Set<UUID> coursesId = courses.stream().map(Course::getId).collect(Collectors.toSet());


        final CreateSubscriptionUseCaseInput input = CreateSubscriptionUseCaseInput.builder()
                .studentId(student.getId())
                .courseIds(coursesId)
                .build();

        CreateSubscriptionUseCaseOutput output = useCase.execute(input);

        verify(subscriptionPersistence, times(3)).save(any());

    }


    private User givenStudent() {
        final User student = anUser().build();
        when(userPersistence.get(any())).thenReturn(student);
        return student;
    }

    private List<Course> givenCourses() {
        final Course course1 = Courses.aCourse().title("Course 1").price(20.10).build();
        final Course course2 = Courses.aCourse().title("Course 2").price(19.70).build();
        final Course course3 = Courses.aCourse().title("Course 3").price(23.20).build();

        final List<Course> courses = List.of(course1, course2, course3);

        courses.forEach((course) -> when(coursePersistence.get(course.getId())).thenReturn(course));
        return courses;
    }
}