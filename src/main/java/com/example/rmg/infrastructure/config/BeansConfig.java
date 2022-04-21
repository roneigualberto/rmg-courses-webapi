package com.example.rmg.infrastructure.config;


import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.usecase.category.create.CreateCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {


    @Bean
    public CreateCategoryUseCase categoryUseCase(CategoryPersistence categoryPersistence) {
        return new CreateCategoryUseCase(categoryPersistence);
    }
}
