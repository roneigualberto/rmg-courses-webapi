package com.example.rmg.application.rest.user;

import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntity;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntityRepository;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntity;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntityRepository;
import com.example.rmg.infrastructure.persistence.jpa.paymentmethod.PaymentMethodEntity;
import com.example.rmg.infrastructure.persistence.jpa.paymentmethod.PaymentMethodEntityRepository;
import com.example.rmg.infrastructure.persistence.jpa.purchase.PurchaseEntity;
import com.example.rmg.infrastructure.persistence.jpa.purchase.PurchaseItemEntity;
import com.example.rmg.infrastructure.persistence.jpa.purchase.PurchaseEntityRepository;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntityRepository;
import com.example.rmg.infrastructure.test.builders.Categories;
import com.example.rmg.infrastructure.test.builders.Courses;
import com.example.rmg.infrastructure.test.builders.Purchases;
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

import java.util.Set;
import java.util.UUID;

import static com.example.rmg.infrastructure.test.builders.PaymentMethods.aPaymentMethodEntity;
import static com.example.rmg.infrastructure.test.builders.PaymentMethods.aPaymentRequest;
import static com.example.rmg.infrastructure.test.builders.Users.anUserEntity;
import static com.example.rmg.infrastructure.test.builders.Users.anUserRequest;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Autowired
    private CategoryEntityRepository categoryEntityRepository;

    @Autowired
    private CourseEntityRepository courseEntityRepository;

    @Autowired
    private PurchaseEntityRepository purchaseEntityRepository;

    private UserEntity userEntity;
    private PaymentMethodResponse paymentMethodResponse;
    private UserResponse userResponse;
    private PaymentMethodEntity paymentMethodEntity;
    private CategoryEntity categoryEntity;
    private CourseEntity courseEntity;
    private PurchaseResponse purchaseResponse;
    private PurchaseEntity purchaseEntity;


    @AfterEach
    void tearDown() {

        if (paymentMethodEntity != null) {
            paymentMethodEntityRepository.delete(paymentMethodEntity);
        }

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

    @Test
    @Transactional
    void should_make_purchase() throws Exception {
        givenUserEntity();
        givenPaymentMethodEntity();
        givenCategoryEntity();
        givenCourseEntity();


        final PurchaseRequest request = PurchaseRequest.builder()
                .coursesId(Set.of(courseEntity.getId()))
                .paymentMethodId(paymentMethodEntity.getId())
                .build();

        MockHttpServletResponse response = mockMvc.perform(post(BASE_URI + "/{userId}/purchases", userEntity.getId())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.buyerId").value(userEntity.getId().toString()))
                .andReturn().getResponse();

        purchaseResponse = objectMapper.readValue(response.getContentAsString(), PurchaseResponse.class);
    }

    @Test
    @Transactional
    void should_list_payment_methods() throws Exception {
        givenUserEntity();
        givenPaymentMethodEntity();
        mockMvc.perform(get(BASE_URI + "/{userId}/payment-methods", userEntity.getId())
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(paymentMethodEntity.getId().toString()))
                .andExpect(jsonPath("$[0].ownerId").value(userEntity.getId().toString()))
                .andExpect(jsonPath("$[0].brand").value(paymentMethodEntity.getBrand().name()))
                .andExpect(jsonPath("$[0].cardNumber").value(paymentMethodEntity.getCardNumber()))
                .andExpect(jsonPath("$[0].nameOnCard").value(paymentMethodEntity.getNameOnCard()))
                .andExpect(jsonPath("$[0].expirationYear").value(paymentMethodEntity.getExpirationYear()))
                .andExpect(jsonPath("$[0].expirationMonth").value(paymentMethodEntity.getExpirationMonth()));


    }

    @Test
    @Transactional
    void should_list_purchases() throws Exception {
        givenUserEntity();
        givenPaymentMethodEntity();
        givenCategoryEntity();
        givenCourseEntity();
        givenPurchaseEntity();

        mockMvc.perform(get(BASE_URI + "/{userId}/purchases", userEntity.getId())
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(purchaseEntity.getId().toString()))
                .andExpect(jsonPath("$[0].buyerId").value(userEntity.getId().toString()));
    }

    private void givenPaymentMethodEntity() {
        paymentMethodEntity = aPaymentMethodEntity(userEntity).build();
        paymentMethodEntity = paymentMethodEntityRepository.save(paymentMethodEntity);
    }

    private void givenUserEntity() {
        userEntity = anUserEntity().build();
        userEntity = userEntityRepository.save(userEntity);
    }

    private void givenCourseEntity() {
        courseEntity = Courses.aCourseEntity(categoryEntity, userEntity).build();
        courseEntityRepository.save(courseEntity);
    }

    private void givenCategoryEntity() {
        categoryEntity = Categories.aCategoryEntity().build();
        categoryEntityRepository.save(categoryEntity);
    }

    private void givenPurchaseEntity() {

        purchaseEntity = Purchases.aPurchaseEntity(userEntity, paymentMethodEntity).build();

        PurchaseItemEntity purchaseItem = PurchaseItemEntity.builder()
                .id(UUID.randomUUID())
                .course(courseEntity)
                .price(courseEntity.getPrice())
                .build();
        purchaseEntity.addItem(purchaseItem);

        purchaseEntityRepository.save(purchaseEntity);

    }

}