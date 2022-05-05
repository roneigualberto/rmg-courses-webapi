package com.example.rmg.infrastructure.application.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.rmg.usecase.category.common.ouput.CategoryView;
import com.example.rmg.usecase.category.list.ListCategoryUseCase;
import com.example.rmg.usecase.category.list.ListCategoryUseCaseInput;
import com.example.rmg.usecase.category.list.ListCategoryUseCaseOutput;
import com.example.rmg.usecase.course.common.output.CourseView;
import com.example.rmg.usecase.course.list.ListCourseUseCase;
import com.example.rmg.usecase.course.list.ListCourseUseCaseInput;
import com.example.rmg.usecase.course.list.ListCourseUseCaseOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {


    private final ListCategoryUseCase listCategoryUseCase;
    private final ListCourseUseCase listCourseUseCase;

    public List<CategoryView> getCategories() {

        ListCategoryUseCaseInput input = ListCategoryUseCaseInput.builder().build();

        ListCategoryUseCaseOutput output = listCategoryUseCase.execute(input);

        return output.getCategories();
    }

    public List<CourseView> getCourses() {

        ListCourseUseCaseInput input = ListCourseUseCaseInput.builder().build();

        ListCourseUseCaseOutput output = listCourseUseCase.execute(input);

        return output.getCourses();
    }

}
