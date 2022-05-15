package com.example.rmg.infrastructure.application.rest.category;


import com.example.rmg.usecase.category.create.CreateCategoryUseCase;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseInput;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseOutput;
import com.example.rmg.usecase.category.delete.DeleteCategoryUseCase;
import com.example.rmg.usecase.category.delete.DeleteCategoryUseCaseInput;
import com.example.rmg.usecase.category.delete.DeleteCategoryUseCaseOutput;
import com.example.rmg.usecase.category.find.FindCategoryUseCase;
import com.example.rmg.usecase.category.find.FindCategoryUseCaseInput;
import com.example.rmg.usecase.category.find.FindCategoryUseCaseOutput;
import com.example.rmg.usecase.category.list.ListCategoryUseCase;
import com.example.rmg.usecase.category.list.ListCategoryUseCaseInput;
import com.example.rmg.usecase.category.list.ListCategoryUseCaseOutput;
import com.example.rmg.usecase.category.update.DefaultUpdateCategoryUseCase;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCase;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCaseInput;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCaseOutput;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Tag(name = "Categories")
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CreateCategoryUseCase createCategoryUseCase;

    private final ListCategoryUseCase listCategoryUseCase;

    private final FindCategoryUseCase findCategoryUseCase;

    private final UpdateCategoryUseCase updateCategoryUseCase;

    private final DeleteCategoryUseCase deleteCategoryUseCase;

    private final CategoryMapper categoryMapper;


    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid CategoryRequest request, UriComponentsBuilder uriBuilder) {

        final CreateCategoryUseCaseInput input = categoryMapper.toCreateCategoryUseCaseInput(request);
        final CreateCategoryUseCaseOutput output = createCategoryUseCase.execute(input);
        final CategoryResponse response = categoryMapper.toCategoryResponse(output.getCategory());
        final URI location = uriBuilder.buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> list() {

        final ListCategoryUseCaseInput input = ListCategoryUseCaseInput.builder()
                .build();

        final ListCategoryUseCaseOutput output = listCategoryUseCase.execute(input);

        final List<CategoryResponse> response = categoryMapper.toCategoryResponseList(output.getCategories());

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> get(@PathVariable UUID categoryId) {

        FindCategoryUseCaseInput input = FindCategoryUseCaseInput.builder()
                .categoryId(categoryId)
                .build();

        FindCategoryUseCaseOutput output = findCategoryUseCase.execute(input);

        final CategoryResponse response = categoryMapper.toCategoryResponse(output.getCategory());

        return ResponseEntity.ok(response);

    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(@PathVariable UUID categoryId, @RequestBody @Valid CategoryRequest request) {

        UpdateCategoryUseCaseInput input = categoryMapper.toUpdateCategoryUseCaseInput(request);

        input.setCategoryId(categoryId);

        UpdateCategoryUseCaseOutput output = updateCategoryUseCase.execute(input);

        final CategoryResponse response = categoryMapper.toCategoryResponse(output.getCategory());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable UUID categoryId) {

        DeleteCategoryUseCaseInput input = DeleteCategoryUseCaseInput.builder()
                .categoryId(categoryId)
                .build();

        DeleteCategoryUseCaseOutput output = deleteCategoryUseCase.execute(input);

        return ResponseEntity.noContent().build();
    }


}
