package com.example.rmg.usecase.category.find;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.messages.CategoryMessages;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.infrastructure.test.builders.Categories;
import com.example.rmg.usecase.category.common.ouput.CategoryView;
import com.example.rmg.usecase.category.find.FindCategoryUseCase;
import com.example.rmg.usecase.category.find.FindCategoryUseCaseInput;
import com.example.rmg.usecase.category.find.FindCategoryUseCaseOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.example.rmg.domain.category.messages.CategoryMessages.CATEGORY_NOT_FOUND;
import static com.example.rmg.domain.category.valueobject.CategoryGroup.DEVELOPMENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FindCategoryUseCaseTest {

    @InjectMocks
    private FindCategoryUseCase useCase;

    @Spy
    private CategoryPersistence categoryPersistence;

    @Test
    void execute_should_find_category_by_id() {

        FindCategoryUseCaseInput input = FindCategoryUseCaseInput.builder()
                .categoryId(UUID.randomUUID())
                .build();

        Category categoryMock = Categories.aCategory().build();

        when(categoryPersistence.findById(any())).thenReturn(Optional.of(categoryMock));

        FindCategoryUseCaseOutput output = useCase.execute(input);

        CategoryView categoryView = output.getCategory();


        assertEquals(categoryView.getName(), categoryMock.getName());
        assertEquals(categoryView.getGroup(), categoryMock.getGroup());
    }

    @Test
    void execute_should_not_find_category_by_id() {

        FindCategoryUseCaseInput input = FindCategoryUseCaseInput.builder()
                .categoryId(UUID.randomUUID())
                .build();

        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));


        assertEquals(exc.getMessage(), CATEGORY_NOT_FOUND);


    }
}