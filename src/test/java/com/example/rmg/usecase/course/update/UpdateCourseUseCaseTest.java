package com.example.rmg.usecase.course.update;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.infrastructure.test.builders.Courses;
import com.example.rmg.usecase.course.common.output.CourseView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.rmg.infrastructure.test.builders.Categories.aCategory;
import static com.example.rmg.infrastructure.test.builders.Users.anUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UpdateCourseUseCaseTest {

    @InjectMocks
    private UpdateCourseUseCase useCase;

    @Spy
    private CoursePersistence coursePersistence;

    @Mock
    private CategoryPersistence categoryPersistence;

    @Mock
    private UserPersistence userPersistence;


    @Test
    void execute_should_update_course() {

        Category categoryMock = aCategory().build();

        when(categoryPersistence.get(any())).thenReturn(categoryMock);

        User instructorMock = anUser().build();

        when(userPersistence.get(any())).thenReturn(instructorMock);


        Course course = Courses.aCourse()
                .instructor(instructorMock)
                .category(categoryMock)
                .build();

        when(coursePersistence.findById(any())).thenReturn(Optional.of(course));

        UpdateCourseUseCaseInput input = Courses.aUpdateCourseUseCaseInput(course.getId())
                .categoryId(categoryMock.getId())
                .instructorId(instructorMock.getId())
                .build();

        UpdateCourseUseCaseOutput output = useCase.execute(input);

        CourseView courseView = output.getCourse();

        assertEquals(courseView.getId(), course.getId());
        assertEquals(courseView.getName(), input.getName());
        assertEquals(courseView.getSkillLevel(), input.getSkillLevel());
        assertEquals(courseView.getPrice(), input.getPrice());
        assertEquals(courseView.getDescription(), input.getDescription());

    }


}