package com.example.rmg.application.rest.course;


import com.example.rmg.application.rest.category.CategoryRequest;
import com.example.rmg.application.rest.category.CategoryResponse;
import com.example.rmg.usecase.category.create.CreateCategoryUseCase;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseInput;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseOutput;
import com.example.rmg.usecase.category.find.FindCategoryUseCaseInput;
import com.example.rmg.usecase.category.find.FindCategoryUseCaseOutput;
import com.example.rmg.usecase.category.list.ListCategoryUseCaseInput;
import com.example.rmg.usecase.category.list.ListCategoryUseCaseOutput;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCase;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCaseInput;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCaseOutput;
import com.example.rmg.usecase.course.create.CreateCourseUseCase;
import com.example.rmg.usecase.course.create.CreateCourseUseCaseInput;
import com.example.rmg.usecase.course.create.CreateCourseUseCaseOutput;
import com.example.rmg.usecase.course.find.FindCourseUseCase;
import com.example.rmg.usecase.course.find.FindCourseUseCaseInput;
import com.example.rmg.usecase.course.find.FindCourseUseCaseOutput;
import com.example.rmg.usecase.course.list.ListCourseUseCase;
import com.example.rmg.usecase.course.list.ListCourseUseCaseInput;
import com.example.rmg.usecase.course.list.ListCourseUseCaseOutput;
import com.example.rmg.usecase.course.update.UpdateCourseUseCase;
import com.example.rmg.usecase.course.update.UpdateCourseUseCaseInput;
import com.example.rmg.usecase.course.update.UpdateCourseUseCaseOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CreateCourseUseCase createCourseUseCase;

    private final ListCourseUseCase listCourseUseCase;

    private final FindCourseUseCase findCourseUseCase;

    private final UpdateCourseUseCase updateCourseUseCase;

    private final CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<CourseResponse> create(@RequestBody @Valid CourseRequest request, UriComponentsBuilder uriBuilder) {

        final CreateCourseUseCaseInput input = courseMapper.toCreateCourseUseCaseInput(request);
        final CreateCourseUseCaseOutput output = createCourseUseCase.execute(input);
        final CourseResponse response = courseMapper.toCourseResponse(output.getCourse());

        final URI location = uriBuilder.buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> list() {

        final ListCourseUseCaseInput input = ListCourseUseCaseInput.builder()
                .build();

        final ListCourseUseCaseOutput output = listCourseUseCase.execute(input);

        final List<CourseResponse> response = courseMapper.toCourseResponseList(output.getCourses());

        return ResponseEntity.ok(response);

    }


    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> get(@PathVariable UUID courseId) {

        FindCourseUseCaseInput input = FindCourseUseCaseInput.builder()
                .courseId(courseId)
                .build();

        FindCourseUseCaseOutput output = findCourseUseCase.execute(input);

        final CourseResponse response = courseMapper.toCourseResponse(output.getCourse());

        return ResponseEntity.ok(response);

    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponse> update(@PathVariable UUID courseId, @RequestBody @Valid CourseRequest request) {

        UpdateCourseUseCaseInput input = courseMapper.toUpdateCourseUseCaseInput(request);

        input.setCourseId(courseId);

        UpdateCourseUseCaseOutput output = updateCourseUseCase.execute(input);

        final CourseResponse response = courseMapper.toCourseResponse(output.getCourse());

        return ResponseEntity.ok(response);
    }

}
