package com.example.rmg.infrastructure.config;


import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.usecase.category.create.CreateCategoryUseCase;
import com.example.rmg.usecase.category.delete.DeleteCategoryUseCase;
import com.example.rmg.usecase.category.find.FindCategoryUseCase;
import com.example.rmg.usecase.category.list.ListCategoryUseCase;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(CategoryPersistence categoryPersistence) {
        return new CreateCategoryUseCase(categoryPersistence);
    }

    @Bean
    public ListCategoryUseCase listCategoryUseCase(CategoryPersistence categoryPersistence) {
        return new ListCategoryUseCase(categoryPersistence);
    }

    @Bean
    public FindCategoryUseCase findCategoryUseCase(CategoryPersistence categoryPersistence) {
        return new FindCategoryUseCase(categoryPersistence);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase(CategoryPersistence categoryPersistence) {
        return new UpdateCategoryUseCase(categoryPersistence);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase(CategoryPersistence categoryPersistence) {
        return new DeleteCategoryUseCase(categoryPersistence);
    }

}
