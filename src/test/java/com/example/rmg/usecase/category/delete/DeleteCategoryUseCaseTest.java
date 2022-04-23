package com.example.rmg.usecase.category.delete;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.infrastructure.test.builders.Categories;
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
class DeleteCategoryUseCaseTest {


    @InjectMocks
    private DeleteCategoryUseCase useCase;


    @Spy
    private CategoryPersistence categoryPersistence;

    @Test
    void execute_should_delete_category() {

        Category categoryMock = Categories.aCategory().build();

        when(categoryPersistence.findById(any())).thenReturn(Optional.of(categoryMock));

        DeleteCategoryUseCaseInput input = DeleteCategoryUseCaseInput.builder()
                .categoryId(categoryMock.getId())
                .build();

        DeleteCategoryUseCaseOutput output = useCase.execute(input);

        verify(categoryPersistence).deleteById(any());


    }
}