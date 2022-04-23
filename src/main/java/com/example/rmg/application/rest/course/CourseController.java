package com.example.rmg.application.rest.course;


import com.example.rmg.application.rest.category.CategoryRequest;
import com.example.rmg.application.rest.category.CategoryResponse;
import com.example.rmg.usecase.category.create.CreateCategoryUseCase;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseInput;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseOutput;
import com.example.rmg.usecase.category.delete.DeleteCategoryUseCaseInput;
import com.example.rmg.usecase.category.delete.DeleteCategoryUseCaseOutput;
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
import com.example.rmg.usecase.course.delete.DeleteCourseUseCase;
import com.example.rmg.usecase.course.delete.DeleteCourseUseCaseInput;
import com.example.rmg.usecase.course.delete.DeleteCourseUseCaseOutput;
import com.example.rmg.usecase.course.find.FindCourseUseCase;
import com.example.rmg.usecase.course.find.FindCourseUseCaseInput;
import com.example.rmg.usecase.course.find.FindCourseUseCaseOutput;
import com.example.rmg.usecase.course.lecture.create.CreateLectureUseCase;
import com.example.rmg.usecase.course.lecture.create.CreateLectureUseCaseInput;
import com.example.rmg.usecase.course.lecture.create.CreateLectureUseCaseOutput;
import com.example.rmg.usecase.course.lecture.list.ListLectureUseCase;
import com.example.rmg.usecase.course.lecture.list.ListLectureUseCaseInput;
import com.example.rmg.usecase.course.lecture.list.ListLectureUseCaseOutput;
import com.example.rmg.usecase.course.list.ListCourseUseCase;
import com.example.rmg.usecase.course.list.ListCourseUseCaseInput;
import com.example.rmg.usecase.course.list.ListCourseUseCaseOutput;
import com.example.rmg.usecase.course.publish.PublishCourseUseCase;
import com.example.rmg.usecase.course.publish.PublishCourseUseCaseInput;
import com.example.rmg.usecase.course.publish.PublishCourseUseCaseOutput;
import com.example.rmg.usecase.course.update.UpdateCourseUseCase;
import com.example.rmg.usecase.course.update.UpdateCourseUseCaseInput;
import com.example.rmg.usecase.course.update.UpdateCourseUseCaseOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    private final DeleteCourseUseCase deleteCourseUseCase;

    private final PublishCourseUseCase publishCourseUseCase;

    private final CreateLectureUseCase createLectureUseCase;

    private final ListLectureUseCase listLectureUseCase;

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

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> delete(@PathVariable UUID courseId) {

        DeleteCourseUseCaseInput input = DeleteCourseUseCaseInput.builder()
                .courseId(courseId)
                .build();

        DeleteCourseUseCaseOutput output = deleteCourseUseCase.execute(input);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{courseId}/publish")
    public ResponseEntity<?> publish(@PathVariable UUID courseId) {

        PublishCourseUseCaseInput input = PublishCourseUseCaseInput.builder()
                .courseId(courseId)
                .build();

        PublishCourseUseCaseOutput output = publishCourseUseCase.execute(input);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PostMapping("{courseId}/lectures")
    public ResponseEntity<LectureResponse> create(
            @PathVariable UUID courseId,
            @RequestBody @Valid LectureRequest request, UriComponentsBuilder uriBuilder) {


        final CreateLectureUseCaseInput input = CreateLectureUseCaseInput.builder()
                .courseId(courseId)
                .lecture(courseMapper.toLectureForm(request))
                .build();

        final CreateLectureUseCaseOutput output = createLectureUseCase.execute(input);

        final LectureResponse response = courseMapper.toLectureResponse(output.getLecture());

        final URI location = uriBuilder.buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }


    @GetMapping("{courseId}/lectures")
    public ResponseEntity<List<LectureResponse>> create(
            @PathVariable UUID courseId) {

        final ListLectureUseCaseInput input = ListLectureUseCaseInput.builder()
                .courseId(courseId)
                .build();

        ListLectureUseCaseOutput output = listLectureUseCase.execute(input);

        final List<LectureResponse> response = courseMapper.toLectureResponseList(output.getLectures());

        return ResponseEntity.ok(response);
    }


}
