package com.example.rmg.usecase.course.create;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.course.common.output.CourseView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.rmg.infrastructure.test.builders.Categories.aCategory;
import static com.example.rmg.infrastructure.test.builders.Courses.aCreateCourseUseCaseInput;
import static com.example.rmg.infrastructure.test.builders.Users.anUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateCourseUseCaseTest {


    @InjectMocks
    private CreateCourseUseCase useCase;


    @Mock
    private CoursePersistence coursePersistence;

    @Mock
    private CategoryPersistence categoryPersistence;

    @Mock
    private UserPersistence userPersistence;

    @Test
    void execute() {

        Category categoryMock = aCategory().build();

        when(categoryPersistence.get(any())).thenReturn(categoryMock);

        User instructorMock = anUser().build();

        when(userPersistence.get(any())).thenReturn(instructorMock);

        CreateCourseUseCaseInput input = aCreateCourseUseCaseInput(categoryMock.getId(), instructorMock.getId()).build();

        CreateCourseUseCaseOutput output = useCase.execute(input);

        CourseView courseView = output.getCourse();

        assertNotNull(courseView.getId());
        assertEquals(courseView.getName(), input.getName());
        assertEquals(courseView.getTitle(), input.getTitle());
        assertEquals(courseView.getDescription(), input.getDescription());
        assertEquals(courseView.getSkillLevel(), input.getSkillLevel());
        assertEquals(courseView.getPrice(), input.getPrice());
        assertEquals(courseView.getCategory().getId(), input.getCategoryId());
        assertEquals(courseView.getInstructor().getId(), input.getInstructorId());

        verify(coursePersistence).save(any());
    }
}