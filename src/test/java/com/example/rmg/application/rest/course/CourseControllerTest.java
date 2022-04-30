package com.example.rmg.application.rest.course;

import com.example.rmg.domain.course.storage.StorageService;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntity;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntityRepository;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntity;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntityRepository;
import com.example.rmg.infrastructure.persistence.jpa.lecture.LectureEntity;
import com.example.rmg.infrastructure.persistence.jpa.lecture.LectureEntityRepository;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntityRepository;
import com.example.rmg.infrastructure.test.builders.Categories;
import com.example.rmg.infrastructure.test.builders.Courses;
import com.example.rmg.infrastructure.test.builders.Lectures;
import com.example.rmg.infrastructure.test.builders.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static com.example.rmg.infrastructure.test.builders.Lectures.aLectureEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

    @Autowired
    private LectureEntityRepository lectureEntityRepository;

    @MockBean
    private StorageService storageService;

    private CategoryEntity categoryEntity;
    private UserEntity userEntity;
    private CourseResponse courseResponse;
    private CourseEntity courseEntity;
    private LectureEntity lectureEntity;


    @AfterEach
    void tearDown() {

        if (courseResponse != null) {
            courseEntityRepository.deleteById(courseResponse.getId());
        }

        if (lectureEntity != null) {
            lectureEntityRepository.delete(lectureEntity);
        }

        if (courseEntity != null) {
            courseEntityRepository.delete(courseEntity);
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

    @Test
    @Transactional
    void should_find_by_id() throws Exception {

        givenCategoryEntity();
        givenUserEntity();
        givenCourseEntity();

        mockMvc.perform(get(BASE_URI + "/" + courseEntity.getId()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(courseEntity.getName()))
                .andExpect(jsonPath("$.title").value(courseEntity.getTitle()))
                .andExpect(jsonPath("$.price").value(courseEntity.getPrice()))
                .andExpect(jsonPath("$.description").value(courseEntity.getDescription()))
                .andExpect(jsonPath("$.skillLevel").value(courseEntity.getSkillLevel().name()))
                .andExpect(jsonPath("$.category.id").value(categoryEntity.getId().toString()))
                .andExpect(jsonPath("$.category.name").value(categoryEntity.getName()))
                .andExpect(jsonPath("$.category.group").value(categoryEntity.getGroup().name()))
                .andExpect(jsonPath("$.instructor.id").value(userEntity.getId().toString()))
                .andExpect(jsonPath("$.instructor.email").value(userEntity.getEmail()))
                .andExpect(jsonPath("$.instructor.firstName").value(userEntity.getFirstName()))
                .andExpect(jsonPath("$.instructor.lastName").value(userEntity.getLastName()))
                .andReturn();
    }

    @Test
    @Transactional
    void should_list_categories() throws Exception {

        givenCategoryEntity();
        givenUserEntity();
        givenCourseEntity();

        mockMvc.perform(get(BASE_URI).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(courseEntity.getId().toString()))
                .andExpect(jsonPath("$[0].name").value(courseEntity.getName()))
                .andExpect(jsonPath("$[0].title").value(courseEntity.getTitle()))
                .andExpect(jsonPath("$[0].price").value(courseEntity.getPrice()))
                .andExpect(jsonPath("$[0].description").value(courseEntity.getDescription()))
                .andExpect(jsonPath("$[0].skillLevel").value(courseEntity.getSkillLevel().name()))
                .andExpect(jsonPath("$[0].category.id").value(categoryEntity.getId().toString()))
                .andExpect(jsonPath("$[0].category.name").value(categoryEntity.getName()))
                .andExpect(jsonPath("$[0].category.group").value(categoryEntity.getGroup().name()))
                .andExpect(jsonPath("$[0].instructor.id").value(userEntity.getId().toString()))
                .andExpect(jsonPath("$[0].instructor.email").value(userEntity.getEmail()))
                .andExpect(jsonPath("$[0].instructor.firstName").value(userEntity.getFirstName()))
                .andExpect(jsonPath("$[0].instructor.lastName").value(userEntity.getLastName()));
    }

    @Test
    @Transactional
    void should_update_course() throws Exception {

        givenCategoryEntity();
        givenUserEntity();
        givenCourseEntity();

        CourseRequest request = Courses.aCourseRequest(categoryEntity.getId(), userEntity.getId())
                .name("Course Updated")
                .price(20.80)
                .build();

        mockMvc.perform(
                        put(BASE_URI + "/" + courseEntity.getId())
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
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
    }

    @Test
    @Transactional
    void should_delete_course() throws Exception {

        givenCategoryEntity();
        givenUserEntity();
        givenCourseEntity();

        UUID courseId = courseEntity.getId();

        mockMvc.perform(
                        delete(BASE_URI + "/" + courseId)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());

        boolean existsCourse = courseEntityRepository.existsById(courseId);

        assertFalse(existsCourse);
    }


    @Test
    @Transactional
    void should_publish_course() throws Exception {

        givenCategoryEntity();
        givenUserEntity();
        givenCourseEntity();

        UUID courseId = courseEntity.getId();

        mockMvc.perform(
                        patch(BASE_URI + "/{courseId}/publish", courseId)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());

        CourseEntity courseEntityFind = courseEntityRepository.findById(courseId).orElse(new CourseEntity());

        assertTrue(courseEntityFind.getPublished());
        assertNotNull(courseEntityFind.getPublishDate());
    }

    @Test
    @Transactional
    void should_create_lecture() throws Exception {

        givenCategoryEntity();
        givenUserEntity();
        givenCourseEntity();

        UUID courseId = courseEntity.getId();
        LectureRequest lectureRequest = Lectures.aLectureRequest().build();
        mockMvc.perform(post(BASE_URI + "/{courseId}/lectures", courseId)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lectureRequest))
                )
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    void should_list_lectures() throws Exception {

        givenCategoryEntity();
        givenUserEntity();
        givenCourseEntity();
        givenLectureEntity();

        mockMvc.perform(get(BASE_URI + "/{courseId}/lectures", courseEntity.getId()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(lectureEntity.getId().toString()))
                .andExpect(jsonPath("$[0].title").value(lectureEntity.getTitle()))
                .andExpect(jsonPath("$[0].order").value(lectureEntity.getOrder()))
                .andExpect(jsonPath("$[0].type").value(lectureEntity.getType().name()));
    }

    @Test
    void should_store_lecture_media() throws Exception {
        givenCategoryEntity();
        givenUserEntity();
        givenCourseEntity();
        givenLectureEntity();

        String url = BASE_URI + "/{courseId}/lectures/{lectureId}/media";

        MockMultipartFile mockMultipartFile = new MockMultipartFile("media", "some-file.html", "text/html", "<p>Lecture Content</p>".getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(multipart(url, courseEntity.getId(), lectureEntity.getId())
                .file(mockMultipartFile)
        ).andExpect(status().isNoContent()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void should_retrieve_lecture_media() throws Exception {
        givenCategoryEntity();
        givenUserEntity();
        givenCourseEntity();
        givenLectureEntity();


        final byte[] mediaBytes = "<p> Lecture Content </p>".getBytes(StandardCharsets.UTF_8);

        final ByteArrayInputStream media = new ByteArrayInputStream(mediaBytes);


        when(storageService.get(Mockito.any(), Mockito.any())).thenReturn(media);

        final String url = BASE_URI + "/{courseId}/lectures/{lectureId}/media";

        mockMvc.perform(get(url, courseEntity.getId(), lectureEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(content().bytes(mediaBytes))
               .andExpect(header().string("Content-Type", lectureEntity.getType().getMimeType()));

    }

    private void givenLectureEntity() {
        lectureEntity = aLectureEntity(courseEntity).build();
        lectureEntityRepository.save(lectureEntity);
    }


    private void givenCourseEntity() {
        courseEntity = Courses.aCourseEntity(categoryEntity, userEntity).build();
        courseEntityRepository.save(courseEntity);
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