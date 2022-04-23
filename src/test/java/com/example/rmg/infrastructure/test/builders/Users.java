package com.example.rmg.infrastructure.test.builders;

import com.example.rmg.application.rest.user.UserRequest;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.usecase.user.create.CreateUserUseCaseInput;

import java.time.LocalDate;
import java.time.Month;

public class Users {

    public static final String USER_FIRST_NAME = "User First Name";
    public static final String USER_LAST_NAME = "User Last Name";
    public static final String USER_EMAIL = "user@email.com";
    public static final String USER_PASSWORD = "strong-password";
    public static final LocalDate BIRTH_DATE = LocalDate.of(1991, Month.AUGUST, 17);


    public static CreateUserUseCaseInput.CreateUserUseCaseInputBuilder aCreateUserUseCaseInput() {
        return CreateUserUseCaseInput
                .builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .birthDate(BIRTH_DATE);
    }

    public static User.UserBuilder anUser() {
        return User
                .builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .birthDate(BIRTH_DATE);
    }

    public static UserRequest.UserRequestBuilder anUserRequest() {
        return UserRequest
                .builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .birthDate(BIRTH_DATE);
    }

}
