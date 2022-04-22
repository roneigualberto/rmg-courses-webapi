package com.example.rmg.application.rest.category;

import com.example.rmg.domain.category.messages.CategoryMessages;
import com.example.rmg.domain.category.valueobject.CategoryGroup;
import com.example.rmg.application.rest.CategoryRequest;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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


    private CategoryEntity categoryEntity;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

        if (categoryEntity != null) {
            categoryEntityRepository.delete(categoryEntity);
        }
    }

    @Test
    void should_create_category_with_existent_name() throws Exception {

        givenCategoryEntity("Category Name", CategoryGroup.BUSINESS);

        CategoryRequest request = CategoryRequest.builder().name("Category Name").group(CategoryGroup.DEVELOPMENT).build();

        MvcResult response = mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.message").value(CategoryMessages.CATEGORY_NAME_EXISTS))
                .andReturn();


        System.out.println(response.getResponse().getContentAsString());


    }

    private void givenCategoryEntity(String name, CategoryGroup group) {
        categoryEntity = CategoryEntity.builder()
                .id(UUID.randomUUID())
                .group(group).name(name).build();

        categoryEntityRepository.save(categoryEntity);
    }

    @Test
    void should_create_category() throws Exception {

        CategoryRequest request = CategoryRequest.builder().name("Category Name").group(CategoryGroup.DEVELOPMENT).build();

        mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Category Name"))
                .andExpect(jsonPath("$.group").value(CategoryGroup.DEVELOPMENT.name()));
    }


    @Test
    @Transactional
    void should_list_categories() throws Exception {


        givenCategoryEntity("Category Name", CategoryGroup.DEVELOPMENT);

        mockMvc.perform(get(BASE_URI).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(categoryEntity.getId().toString()))
                .andExpect(jsonPath("$[0].name").value(categoryEntity.getName()))
                .andExpect(jsonPath("$[0].group").value(categoryEntity.getGroup().name()));
    }

    @Test
    @Transactional
    void should_find_by_id() throws Exception {

        givenCategoryEntity("Category Name", CategoryGroup.DEVELOPMENT);

        UUID categoryId = categoryEntity.getId();

        categoryEntityRepository.save(categoryEntity);

        mockMvc.perform(get(BASE_URI + "/" + categoryId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categoryEntity.getId().toString()))
                .andExpect(jsonPath("$.name").value(categoryEntity.getName()))
                .andExpect(jsonPath("$.group").value(categoryEntity.getGroup().name()));
    }

    @Test
    @Transactional
    void should_update_category() throws Exception {



        givenCategoryEntity("Category Name", CategoryGroup.DEVELOPMENT);

        UUID categoryId = categoryEntity.getId();

        CategoryRequest request = CategoryRequest.builder().name("Category Updated").group(CategoryGroup.BUSINESS).build();

        mockMvc.perform(
                        put(BASE_URI + "/" + categoryId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categoryId.toString()))
                .andExpect(jsonPath("$.name").value(request.getName()))
                .andExpect(jsonPath("$.group").value(request.getGroup().name()));
    }


    @Test
    @Transactional
    void should_delete_category() throws Exception {


        givenCategoryEntity("Category Name", CategoryGroup.DEVELOPMENT);


        UUID categoryId = categoryEntity.getId();

        mockMvc.perform(
                        delete(BASE_URI + "/" + categoryId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());

        boolean existsCategory = categoryEntityRepository.existsById(categoryId);

        assertFalse(existsCategory);
    }

}