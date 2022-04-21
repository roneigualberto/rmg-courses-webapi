package com.example.rmg.application.rest.category;

import com.example.rmg.domain.category.valueobject.CategoryGroup;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntity;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CategoryControllerTest {

    public static final String BASE_URI = "/api/v1/categories";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryEntityRepository categoryEntityRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void should_create_category() throws Exception {

        CategoryRequest request = CategoryRequest.builder().name("Category Name").group(CategoryGroup.DEVELOPMENT).build();

        mockMvc.perform(post(BASE_URI).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated()).andExpect(jsonPath("$.id").isNotEmpty()).andExpect(jsonPath("$.name").value("Category Name")).andExpect(jsonPath("$.group").value(CategoryGroup.DEVELOPMENT.name()));
    }


    @Test
    @Transactional
    void should_list_categories() throws Exception {


        CategoryEntity categoryEntity = CategoryEntity.builder()
                .id(UUID.randomUUID())
                .group(CategoryGroup
                        .DEVELOPMENT).name("Category 1 ").build();

        categoryEntityRepository.save(categoryEntity);

        mockMvc.perform(get(BASE_URI).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(categoryEntity.getId().toString()))
                .andExpect(jsonPath("$[0].name").value(categoryEntity.getName()))
                .andExpect(jsonPath("$[0].group").value(categoryEntity.getGroup().name()));
    }
}