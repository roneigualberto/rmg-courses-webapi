package com.example.rmg.infrastructure.application.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.rmg.infrastructure.application.rest.course.CourseMapper;
import com.example.rmg.infrastructure.application.rest.course.CourseRequest;
import com.example.rmg.usecase.course.common.output.CourseView;
import com.example.rmg.usecase.course.create.CreateCourseUseCase;
import com.example.rmg.usecase.course.create.CreateCourseUseCaseInput;
import com.example.rmg.usecase.course.create.CreateCourseUseCaseOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver {

    private final CreateCourseUseCase createCourseUseCase;

    private final CourseMapper courseMapper;


    @Transactional
    public CourseView createCourse(CourseRequest request) {

        CreateCourseUseCaseInput input = courseMapper.toCreateCourseUseCaseInput(request);
        CreateCourseUseCaseOutput output = createCourseUseCase.execute(input);
        return output.getCourse();
    }


}
