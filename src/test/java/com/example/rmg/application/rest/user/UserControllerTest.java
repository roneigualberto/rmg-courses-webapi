package com.example.rmg.application.rest.user;

import com.example.rmg.infrastructure.persistence.jpa.paymentmethod.PaymentMethodEntityRepository;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.example.rmg.infrastructure.test.builders.PaymentMethods.aPaymentRequest;
import static com.example.rmg.infrastructure.test.builders.Users.anUserEntity;
import static com.example.rmg.infrastructure.test.builders.Users.anUserRequest;
import static org.springframework.http.MediaType.APPLICATION_JSON;
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

    @Autowired
    private PaymentMethodEntityRepository paymentMethodEntityRepository;

    private UserEntity userEntity;
    private PaymentMethodResponse paymentMethodResponse;
    private UserResponse userResponse;

    @AfterEach
    void tearDown() {

        if (paymentMethodResponse != null) {
            paymentMethodEntityRepository.deleteById(paymentMethodResponse.getId());
        }

        if (userResponse != null) {
            userEntityRepository.deleteById(userResponse.getId());
        }

        if (userEntity != null) {
            userEntityRepository.delete(userEntity);
        }
    }


    @Test
    void should_create_user() throws Exception {

        UserRequest request = anUserRequest().build();

        MockHttpServletResponse response = mockMvc.perform(post(BASE_URI)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.firstName").value(request.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(request.getLastName()))
                .andExpect(jsonPath("$.email").value(request.getEmail()))
                .andReturn().getResponse();

        userResponse = objectMapper.readValue(response.getContentAsString(), UserResponse.class);

    }


    @Test
    @Transactional
    void should_create_payment_method() throws Exception {
        givenUserEntity();
        final PaymentMethodRequest request = aPaymentRequest().build();
        MockHttpServletResponse response = mockMvc.perform(post(BASE_URI + "/{userId}/payment-methods", userEntity.getId())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.ownerId").value(userEntity.getId().toString()))
                .andExpect(jsonPath("$.brand").value(request.getBrand().name()))
                .andExpect(jsonPath("$.cardNumber").value(request.getCardNumber()))
                .andExpect(jsonPath("$.nameOnCard").value(request.getNameOnCard()))
                .andExpect(jsonPath("$.expirationYear").value(request.getExpirationYear()))
                .andExpect(jsonPath("$.expirationMonth").value(request.getExpirationMonth()))
                .andReturn().getResponse();

        paymentMethodResponse = objectMapper.readValue(response.getContentAsString(), PaymentMethodResponse.class);
    }

    private void givenUserEntity() {
        userEntity = anUserEntity().build();
        userEntityRepository.save(userEntity);
    }

}