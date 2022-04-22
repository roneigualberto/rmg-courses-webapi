package com.example.rmg.usecase.category.create;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.messages.CategoryMessages;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.category.valueobject.CategoryGroup;
import com.example.rmg.domain.common.exception.ValidationException;
import com.example.rmg.usecase.category.common.ouput.CategoryView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateCategoryUseCaseTest {


    public static final String CATEGORY_NAME = "REST API";

    public static final CategoryGroup CATEGORY_GROUP = CategoryGroup.DEVELOPMENT;

    @InjectMocks
    private CreateCategoryUseCase useCase;


    @Mock
    private CategoryPersistence categoryPersistence;


    @Test
    void execute_should_create_category() {
        //Build Input
        CreateCategoryUseCaseInput input = createCategoryInput(CATEGORY_NAME, CATEGORY_GROUP);

        // Execute Use Case
        CreateCategoryUseCaseOutput output = useCase.execute(input);

        CategoryView categoryView = output.getCategory();

        // Verify Result
        assertNotNull(categoryView.getId());
        assertEquals(CATEGORY_NAME, categoryView.getName());
        assertEquals(CATEGORY_GROUP, categoryView.getGroup());
        verify(categoryPersistence).save(any());
    }

    private CreateCategoryUseCaseInput createCategoryInput(String name, CategoryGroup group) {
        return CreateCategoryUseCaseInput.builder()
                .name(name)
                .group(group)
                .build();
    }

    @Test
    void execute_should_not_create_category_when_name_is_null() {

        //Build Input
        CreateCategoryUseCaseInput input = createCategoryInput(null, CATEGORY_GROUP);

        // Execute Use Case
        ValidationException exc = assertThrows(ValidationException.class, () -> useCase.execute(input));

        assertEquals(CategoryMessages.CATEGORY_NAME_REQUIRED, exc.getErrors().stream().findFirst().get().getMessage());
    }

    @Test
    void execute_should_not_create_category_when_group_is_null() {

        //Build Input
        CreateCategoryUseCaseInput input = createCategoryInput(CATEGORY_NAME, null);

        // Execute Use Case
        ValidationException exc = assertThrows(ValidationException.class, () -> useCase.execute(input));

        assertEquals(CategoryMessages.CATEGORY_GROUP_REQUIRED, exc.getErrors().stream().findFirst().get().getMessage());
    }

    @Test
    void execute_should_not_create_category_when_already_exists() {


        //Build Input
        CreateCategoryUseCaseInput input = createCategoryInput(CATEGORY_NAME, CATEGORY_GROUP);

        Category categoryMock = Category.builder().id(UUID.randomUUID())
                .name(CATEGORY_NAME)
                .group(CATEGORY_GROUP)
                .build();

        when(categoryPersistence.findByName(any())).thenReturn(Optional.of(categoryMock));

        // Execute Use Case
        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));

        assertEquals(CategoryMessages.CATEGORY_NAME_EXISTS, exc.getMessage());


    }
}