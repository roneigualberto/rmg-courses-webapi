package com.example.rmg.usecase.category.update;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.messages.CategoryMessages;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.usecase.category.common.ouput.CategoryView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.example.rmg.domain.category.valueobject.CategoryGroup.BUSINESS;
import static com.example.rmg.domain.category.valueobject.CategoryGroup.DEVELOPMENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCategoryUseCaseTest {

    @InjectMocks
    private UpdateCategoryUseCase useCase;

    @Spy
    private CategoryPersistence categoryPersistence;

    @Test
    void execute_should_update_category() {

        UUID categoryId = UUID.randomUUID();

        UpdateCategoryUseCaseInput input = UpdateCategoryUseCaseInput.builder()
                .categoryId(categoryId)
                .name("Category Updated")
                .group(BUSINESS)
                .build();

        Category categoryMock = Category.builder()
                .id(categoryId)
                .name("Category 1")
                .group(DEVELOPMENT)
                .build();

        when(categoryPersistence.findById(Mockito.any())).thenReturn(Optional.of(categoryMock));

        UpdateCategoryUseCaseOutput output = useCase.execute(input);

        CategoryView categoryView = output.getCategory();

        assertEquals(categoryView.getId(), categoryId);
        assertEquals(categoryView.getName(), "Category Updated");
        assertEquals(categoryView.getGroup(), BUSINESS);
    }

    @Test
    void execute_should_not_update_category_with_same_name() {

        UUID categoryId = UUID.randomUUID();

        UpdateCategoryUseCaseInput input = UpdateCategoryUseCaseInput.builder()
                .categoryId(categoryId)
                .name("Category Updated")
                .group(BUSINESS)
                .build();

        Category categoryMock = Category.builder()
                .id(UUID.randomUUID())
                .name("Category Updated")
                .group(DEVELOPMENT)
                .build();


        when(categoryPersistence.findByName(Mockito.any())).thenReturn(Optional.of(categoryMock));

        // Execute Use Case
        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));

        assertEquals(CategoryMessages.CATEGORY_NAME_EXISTS, exc.getMessage());
    }
}