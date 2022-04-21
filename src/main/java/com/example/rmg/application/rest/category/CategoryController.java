package com.example.rmg.application.rest.category;


import com.example.rmg.usecase.category.common.ouput.CategoryView;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseInput;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseOutput;
import com.example.rmg.usecase.category.create.CreateCategoryUseCase;
import com.example.rmg.usecase.category.find.FindCategoryUseCase;
import com.example.rmg.usecase.category.find.FindCategoryUseCaseInput;
import com.example.rmg.usecase.category.find.FindCategoryUseCaseOutput;
import com.example.rmg.usecase.category.list.ListCategoryUseCase;
import com.example.rmg.usecase.category.list.ListCategoryUseCaseInput;
import com.example.rmg.usecase.category.list.ListCategoryUseCaseOutput;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCase;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCaseInput;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCaseOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CreateCategoryUseCase createCategoryUseCase;

    private final ListCategoryUseCase listCategoryUseCase;

    private final FindCategoryUseCase findCategoryUseCase;

    private final UpdateCategoryUseCase updateCategoryUseCase;


    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid CategoryRequest request, UriComponentsBuilder uriBuilder) {

        CreateCategoryUseCaseInput input = CreateCategoryUseCaseInput.builder()
                .name(request.getName())
                .group(request.getGroup())
                .build();

        CreateCategoryUseCaseOutput output = createCategoryUseCase.execute(input);

        CategoryView category = output.getCategory();

        CategoryResponse response = CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .group(category.getGroup())
                .build();

        URI location = uriBuilder.buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);

    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> list() {

        ListCategoryUseCaseInput input = ListCategoryUseCaseInput.builder()
                .build();

        ListCategoryUseCaseOutput output = listCategoryUseCase.execute(input);

        List<CategoryResponse> response = output.getCategories().stream().map((categoryView) ->
                CategoryResponse.builder()
                        .id(categoryView.getId())
                        .name(categoryView.getName())
                        .group(categoryView.getGroup())
                        .build()
        ).collect(Collectors.toList());

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> get(@PathVariable UUID id) {

        FindCategoryUseCaseInput input = FindCategoryUseCaseInput.builder()
                .categoryId(id)
                .build();

        FindCategoryUseCaseOutput output = findCategoryUseCase.execute(input);

        CategoryView category = output.getCategory();

        CategoryResponse response = CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .group(category.getGroup())
                .build();

        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable UUID id, @RequestBody @Valid CategoryRequest request) {

        UpdateCategoryUseCaseInput input = UpdateCategoryUseCaseInput.builder()
                .categoryId(id)
                .name(request.getName())
                .group(request.getGroup())
                .build();

        UpdateCategoryUseCaseOutput output = updateCategoryUseCase.execute(input);

        CategoryView category = output.getCategory();

        CategoryResponse response = CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .group(category.getGroup())
                .build();

        return ResponseEntity.ok(response);
    }
}
