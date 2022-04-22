package com.example.rmg.application.rest.user;

import com.example.rmg.infrastructure.persistence.jpa.user.UserEntityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {


    public static final String BASE_URI = "/api/v1/users";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserEntityRepository userEntityRepository;


    @Test
    void should_create_user() throws Exception {

        UserRequest request = UserRequest.builder()
                .firstName("First Name")
                .lastName("Last Name")
                .email("ronei@example.com")
                .password("strong-password")
                .birthDate(LocalDate.of(1991, Month.AUGUST, 17))
                .build();

        mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.firstName").value(request.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(request.getLastName()))
                .andExpect(jsonPath("$.email").value(request.getEmail()));

    }

}