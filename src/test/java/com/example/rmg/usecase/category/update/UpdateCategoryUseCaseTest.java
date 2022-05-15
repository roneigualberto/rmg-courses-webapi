package com.example.rmg.usecase.category.update;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.infrastructure.test.builders.Categories;
import com.example.rmg.usecase.category.common.ouput.CategoryView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.example.rmg.domain.category.messages.CategoryMessages.CATEGORY_NAME_EXISTS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCategoryUseCaseTest {

    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;

    @Spy
    private CategoryPersistence categoryPersistence;

    @Test
    void execute_should_update_category() {


        Category categoryMock = Categories.aCategory().build();

        when(categoryPersistence.findById(any())).thenReturn(Optional.of(categoryMock));

        UpdateCategoryUseCaseInput input = Categories.aCreateCategoryUseCaseInput(categoryMock.getId()).build();

        UpdateCategoryUseCaseOutput output = useCase.execute(input);

        CategoryView categoryView = output.getCategory();

        assertEquals(categoryView.getId(), categoryMock.getId());
        assertEquals(categoryView.getName(), input.getName());
        assertEquals(categoryView.getGroup(), input.getGroup());
    }

    @Test
    void execute_should_not_update_category_with_same_name() {

        Category categoryMock = Categories.aCategory().build();
        UUID categoryId = UUID.randomUUID();
        UpdateCategoryUseCaseInput input = Categories.aCreateCategoryUseCaseInput(categoryId).build();


        when(categoryPersistence.findByName(any())).thenReturn(Optional.of(categoryMock));

        // Execute Use Case
        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));

        assertEquals(CATEGORY_NAME_EXISTS, exc.getMessage());
    }
}