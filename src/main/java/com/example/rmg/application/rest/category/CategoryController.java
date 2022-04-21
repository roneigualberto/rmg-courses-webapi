package com.example.rmg.application.rest.category;


import com.example.rmg.usecase.category.create.CreateCategoryInput;
import com.example.rmg.usecase.category.create.CreateCategoryOutput;
import com.example.rmg.usecase.category.create.CreateCategoryUseCase;
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
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CreateCategoryUseCase createCategoryUseCase;


    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid  CategoryRequest request, UriComponentsBuilder uriBuilder) {

        CreateCategoryInput input = CreateCategoryInput.builder()
                .name(request.getName())
                .group(request.getGroup())
                .build();

        CreateCategoryOutput output = createCategoryUseCase.execute(input);

        CategoryResponse response = CategoryResponse.builder()
                .id(output.getId())
                .name(output.getName())
                .group(output.getGroup())
                .build();

        URI location = uriBuilder.buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);

    }
}
