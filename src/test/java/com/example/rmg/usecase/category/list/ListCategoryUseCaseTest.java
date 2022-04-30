package com.example.rmg.usecase.category.list;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.category.valueobject.CategoryGroup;
import com.example.rmg.infrastructure.test.builders.Categories;
import com.example.rmg.usecase.category.common.ouput.CategoryView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ListCategoryUseCaseTest {


    @InjectMocks
    private ListCategoryUseCase useCase;


    @Mock
    private CategoryPersistence categoryPersistence;

    @Test
    void execute_should_list_categories() {
        ListCategoryUseCaseInput input = ListCategoryUseCaseInput.builder().build();

        List<Category> categoriesMock = createCategoriesMock();

        when(categoryPersistence.findAll()).thenReturn(categoriesMock);


        ListCategoryUseCaseOutput output = useCase.execute(input);

        for (int i = 0; i < output.getCategories().size(); i++) {
            CategoryView categoryResult = output.getCategories().get(i);
            Category categoryExpected = categoriesMock.get(i);
            assertEquals(categoryExpected.getName(), categoryResult.getName());
            assertEquals(categoryExpected.getGroup(), categoryResult.getGroup());
        }

    }

    private List<Category> createCategoriesMock() {
        List<Category> categoriesMock = new ArrayList<>();
        Category category1 = Categories.aCategory()
                .name("Category 1")
                .group(CategoryGroup.DEVELOPMENT)
                .build();

        categoriesMock.add(category1);

        Category category2 = Categories.aCategory()
                .name("Category 2")
                .group(CategoryGroup.DESIGN)
                .build();

        categoriesMock.add(category2);

        Category category3 = Categories.aCategory()
                .name("Category 3")
                .group(CategoryGroup.BUSINESS)
                .build();
        categoriesMock.add(category3);

        return categoriesMock;
    }
}