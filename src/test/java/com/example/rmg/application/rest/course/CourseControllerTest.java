package com.example.rmg.application.rest.course;

import com.example.rmg.application.rest.category.CategoryRequest;
import com.example.rmg.application.rest.course.CourseRequest;
import com.example.rmg.application.rest.course.CourseResponse;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntity;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntityRepository;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntityRepository;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntityRepository;
import com.example.rmg.infrastructure.test.builders.Categories;
import com.example.rmg.infrastructure.test.builders.Courses;
import com.example.rmg.infrastructure.test.builders.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.example.rmg.infrastructure.test.builders.Categories.aCategoryRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CourseControllerTest {

    public static final String BASE_URI = "/api/v1/courses";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CourseEntityRepository courseEntityRepository;

    @Autowired
    private CategoryEntityRepository categoryEntityRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    private CategoryEntity categoryEntity;
    private UserEntity userEntity;
    private CourseResponse courseResponse;


    @AfterEach
    void tearDown() {

        if (courseResponse != null) {
            courseEntityRepository.deleteById(courseResponse.getId());
        }

        if (categoryEntity != null) {
            categoryEntityRepository.delete(categoryEntity);
        }

        if (userEntity != null) {
            userEntityRepository.delete(userEntity);
        }
    }

    @Test
    void should_create_course() throws Exception {

        givenCategoryEntity();
        givenUserEntity();

        CourseRequest request = Courses.aCourseRequest(categoryEntity.getId(), userEntity.getId()).build();

        MvcResult result = mockMvc.perform(post(BASE_URI)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(request.getName()))
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.price").value(request.getPrice()))
                .andExpect(jsonPath("$.description").value(request.getDescription()))
                .andExpect(jsonPath("$.skillLevel").value(request.getSkillLevel().name()))
                .andExpect(jsonPath("$.category.id").value(categoryEntity.getId().toString()))
                .andExpect(jsonPath("$.category.name").value(categoryEntity.getName()))
                .andExpect(jsonPath("$.category.group").value(categoryEntity.getGroup().name()))
                .andExpect(jsonPath("$.instructor.id").value(userEntity.getId().toString()))
                .andExpect(jsonPath("$.instructor.email").value(userEntity.getEmail()))
                .andExpect(jsonPath("$.instructor.firstName").value(userEntity.getFirstName()))
                .andExpect(jsonPath("$.instructor.lastName").value(userEntity.getLastName()))
                .andReturn();

        courseResponse = objectMapper.readValue(result.getResponse().getContentAsString(), CourseResponse.class);

    }

    private void givenUserEntity() {
        userEntity = Users.anUserEntity().build();
        userEntityRepository.save(userEntity);
    }

    private void givenCategoryEntity() {
        categoryEntity = Categories.aCategoryEntity().build();
        categoryEntityRepository.save(categoryEntity);
    }

}