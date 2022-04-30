package com.example.rmg.usecase.category.create;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.common.exception.ValidationException;
import com.example.rmg.infrastructure.test.builders.Categories;
import com.example.rmg.usecase.category.common.ouput.CategoryView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.rmg.domain.category.messages.CategoryMessages.*;
import static com.example.rmg.infrastructure.test.builders.Categories.aCreateCategoryUseCaseInput;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateCategoryUseCaseTest {


    @InjectMocks
    private CreateCategoryUseCase useCase;


    @Mock
    private CategoryPersistence categoryPersistence;


    @Test
    void execute_should_create_category() {
        //Build Input
        CreateCategoryUseCaseInput input = aCreateCategoryUseCaseInput().build();

        // Execute Use Case
        CreateCategoryUseCaseOutput output = useCase.execute(input);

        CategoryView categoryView = output.getCategory();

        // Verify Result
        assertNotNull(categoryView.getId());
        assertEquals(Categories.CATEGORY_NAME, categoryView.getName());
        assertEquals(Categories.CATEGORY_GROUP, categoryView.getGroup());
        verify(categoryPersistence).save(any());
    }

    @Test
    void execute_should_not_create_category_when_name_is_null() {

        //Build Input
        CreateCategoryUseCaseInput input = aCreateCategoryUseCaseInput()
                .name(null)
                .build();

        // Execute Use Case
        ValidationException exc = assertThrows(ValidationException.class, () -> useCase.execute(input));

        assertEquals(CATEGORY_NAME_REQUIRED, exc.getErrors().stream().findFirst().get().getMessage());
    }

    @Test
    void execute_should_not_create_category_when_group_is_null() {

        //Build Input
        CreateCategoryUseCaseInput input = aCreateCategoryUseCaseInput()
                .group(null)
                .build();

        // Execute Use Case
        ValidationException exc = assertThrows(ValidationException.class, () -> useCase.execute(input));

        assertEquals(CATEGORY_GROUP_REQUIRED, exc.getErrors().stream().findFirst().get().getMessage());
    }

    @Test
    void execute_should_not_create_category_when_already_exists() {
        //Build Input
        CreateCategoryUseCaseInput input = aCreateCategoryUseCaseInput().build();

        Category categoryMock = Categories.aCategory().build();

        when(categoryPersistence.findByName(any())).thenReturn(Optional.of(categoryMock));

        // Execute Use Case
        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));

        assertEquals(CATEGORY_NAME_EXISTS, exc.getMessage());


    }
}