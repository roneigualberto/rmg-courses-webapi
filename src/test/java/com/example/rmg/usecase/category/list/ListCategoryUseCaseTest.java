package com.example.rmg.usecase.category.list;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.category.valueobject.CategoryGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ListCategoryUseCaseTest {


    @InjectMocks
    private ListCategoryUseCase useCase;


    @Mock
    private CategoryPersistence categoryPersistence;

    @Test
    void execute() {
        ListCategoryUseCaseInput input = ListCategoryUseCaseInput.builder().build();

        List<Category> categoriesMock = createCategoriesMock();

        when(categoryPersistence.findAll()).thenReturn(categoriesMock);


        ListCategoryUseCaseOutput output = useCase.execute(input);

        assertEquals(output.getCategories().size(), 3);
        assertEquals(output.getCategories().get(0).getName(), "Category 1");
        assertEquals(output.getCategories().get(0).getGroup(), CategoryGroup.DEVELOPMENT);

    }

    private List<Category> createCategoriesMock() {
        List<Category> categoriesMock = new ArrayList<>();
        Category category1 = Category.builder().id(UUID.randomUUID())
                .name("Category 1")
                .group(CategoryGroup.DEVELOPMENT)
                .build();

        categoriesMock.add(category1);

        Category category2 = Category.builder().id(UUID.randomUUID())
                .name("Category 2")
                .group(CategoryGroup.BUSINESS)
                .build();

        categoriesMock.add(category2);

        Category category3 = Category.builder().id(UUID.randomUUID())
                .name("Category 3")
                .group(CategoryGroup.DESIGN)
                .build();
        categoriesMock.add(category3);

        return categoriesMock;
    }
}