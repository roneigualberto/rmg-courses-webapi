package com.example.rmg.usecase.course.delete;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.infrastructure.test.builders.Categories;
import com.example.rmg.infrastructure.test.builders.Courses;
import com.example.rmg.usecase.category.delete.DeleteCategoryUseCase;
import com.example.rmg.usecase.category.delete.DeleteCategoryUseCaseInput;
import com.example.rmg.usecase.category.delete.DeleteCategoryUseCaseOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DeleteCourseUseCaseTest {


    @InjectMocks
    private DeleteCourseUseCase useCase;


    @Spy
    private CoursePersistence coursePersistence;

    @Test
    void execute_should_delete_course() {

        Course course = Courses.aCourse().build();

        when(coursePersistence.findById(any())).thenReturn(Optional.of(course));

        DeleteCourseUseCaseInput input = DeleteCourseUseCaseInput.builder()
                .courseId(course.getId())
                .build();

        DeleteCourseUseCaseOutput output = useCase.execute(input);

        verify(coursePersistence).deleteById(any());


    }
}