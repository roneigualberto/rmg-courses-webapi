package com.example.rmg.usecase.category.find;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.messages.CategoryMessages;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.category.shared.DomainException;
import com.example.rmg.domain.category.valueobject.CategoryGroup;
import com.example.rmg.usecase.category.common.CategoryView;
import com.example.rmg.usecase.category.list.ListCategoryUseCase;
import org.junit.jupiter.api.Assertions;
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

        Category categoryMock = Category.builder().id(UUID.randomUUID())
                .id(UUID.randomUUID())
                .name("Category 1")
                .group(DEVELOPMENT)
                .build();

        when(categoryPersistence.findById(Mockito.any())).thenReturn(Optional.of(categoryMock));

        FindCategoryUseCaseOutput output = useCase.execute(input);

        CategoryView categoryView = output.getCategory();


        assertEquals(categoryView.getName(), "Category 1");
        assertEquals(categoryView.getGroup(), DEVELOPMENT);
    }

    @Test
    void execute_should_not_find_category_by_id() {

        FindCategoryUseCaseInput input = FindCategoryUseCaseInput.builder()
                .categoryId(UUID.randomUUID())
                .build();

        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));


        assertEquals(exc.getMessage(), CategoryMessages.CATEGORY_NOT_FOUND);


    }
}