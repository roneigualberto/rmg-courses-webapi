package com.example.rmg.application.rest.course;


import com.example.rmg.application.rest.category.CategoryRequest;
import com.example.rmg.application.rest.category.CategoryResponse;
import com.example.rmg.usecase.category.create.CreateCategoryUseCase;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseInput;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseOutput;
import com.example.rmg.usecase.course.create.CreateCourseUseCase;
import com.example.rmg.usecase.course.create.CreateCourseUseCaseInput;
import com.example.rmg.usecase.course.create.CreateCourseUseCaseOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CreateCourseUseCase createCourseUseCase;

    private final CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<CourseResponse> create(@RequestBody @Valid CourseRequest request, UriComponentsBuilder uriBuilder) {

        final CreateCourseUseCaseInput input = courseMapper.toCreateCourseUseCaseInput(request);
        final CreateCourseUseCaseOutput output = createCourseUseCase.execute(input);
        final CourseResponse response = courseMapper.toCourseResponse(output.getCourse());


        final URI location = uriBuilder.buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }

}
