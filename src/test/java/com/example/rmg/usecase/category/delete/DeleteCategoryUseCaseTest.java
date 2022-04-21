package com.example.rmg.usecase.category.delete;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.usecase.category.create.CreateCategoryUseCase;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.example.rmg.domain.category.valueobject.CategoryGroup.DEVELOPMENT;
import static org.junit.jupiter.api.Assertions.*;
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
        UUID categoryId = UUID.randomUUID();


        Category categoryMock = Category.builder()
                .id(categoryId)
                .name("Category 1")
                .group(DEVELOPMENT)
                .build();
        when(categoryPersistence.findById(Mockito.any())).thenReturn(Optional.of(categoryMock));

        DeleteCategoryUseCaseInput input = DeleteCategoryUseCaseInput.builder()
                .categoryId(categoryId)
                .build();

        DeleteCategoryUseCaseOutput output = useCase.execute(input);

        verify(categoryPersistence).deleteById(Mockito.any());


    }
}