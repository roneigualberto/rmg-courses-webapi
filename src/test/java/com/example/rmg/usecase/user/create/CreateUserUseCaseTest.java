package com.example.rmg.usecase.user.create;

import com.example.rmg.domain.category.entity.Category;
import com.example.rmg.domain.category.messages.CategoryMessages;
import com.example.rmg.domain.category.valueobject.CategoryGroup;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.messages.UserMessages;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.category.create.CreateCategoryUseCaseInput;
import com.example.rmg.usecase.user.create.common.output.UserView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    public static final String USER_FIRST_NAME = "User First Name";
    public static final String USER_LAST_NAME = "User Last Name";
    public static final String USER_EMAIL = "user@email.com";
    public static final String USER_PASSWORD = "User Password";

    @InjectMocks
    private CreateUserUseCase useCase;

    @Mock
    private UserPersistence userPersistence;

    @Test
    void execute_should_create_user() {
        //Build Input
        CreateUserUseCaseInput input = createUserInput();

        // Execute Use Case
        CreateUserUseCaseOutput output = useCase.execute(input);

        UserView userView = output.getUser();

        // Verify Result
        assertNotNull(userView.getId());
        assertEquals(USER_FIRST_NAME, userView.getFirstName());
        assertEquals(USER_LAST_NAME, userView.getLastName());
        assertEquals(USER_EMAIL, userView.getEmail());
        assertEquals(input.getBirthDate(), userView.getBirthDate());

        verify(userPersistence).save(any());
    }

    private CreateUserUseCaseInput createUserInput() {
        return CreateUserUseCaseInput
                .builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .birthDate(LocalDate.of(1991, Month.AUGUST, 17))
                .build();
    }

    @Test
    void execute_should_not_create_user_when_already_exists() {


        //Build Input
        CreateUserUseCaseInput input = createUserInput();

        User userMock = User.builder().id(UUID.randomUUID())
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .email(USER_EMAIL)
                .build();

        when(userPersistence.findByEmail(any())).thenReturn(Optional.of(userMock));

        // Execute Use Case
        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));

        assertEquals(UserMessages.USER_EMAIL_EXISTS, exc.getMessage());


    }


}