package com.example.rmg.usecase.user.create;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.user.create.CreateUserUseCase;
import com.example.rmg.usecase.user.create.CreateUserUseCaseInput;
import com.example.rmg.usecase.user.create.CreateUserUseCaseOutput;
import com.example.rmg.usecase.user.create.common.output.UserView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.rmg.domain.user.messages.UserMessages.USER_EMAIL_EXISTS;
import static com.example.rmg.infrastructure.test.builders.Users.aCreateUserUseCaseInput;
import static com.example.rmg.infrastructure.test.builders.Users.anUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @InjectMocks
    private CreateUserUseCase useCase;

    @Mock
    private UserPersistence userPersistence;

    @Test
    void execute_should_create_user() {
        //Build Input
        CreateUserUseCaseInput input = aCreateUserUseCaseInput().build();

        // Execute Use Case
        CreateUserUseCaseOutput output = useCase.execute(input);

        UserView userView = output.getUser();

        // Verify Result
        assertNotNull(userView.getId());
        assertEquals(input.getFirstName(), userView.getFirstName());
        assertEquals(input.getLastName(), userView.getLastName());
        assertEquals(input.getEmail(), userView.getEmail());
        assertEquals(input.getBirthDate(), userView.getBirthDate());

        verify(userPersistence).save(any());
    }


    @Test
    void execute_should_not_create_user_when_already_exists() {

        //Build Input
        CreateUserUseCaseInput input = aCreateUserUseCaseInput().build();

        User userMock = anUser().build();
        when(userPersistence.findByEmail(any())).thenReturn(Optional.of(userMock));

        // Execute Use Case
        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));

        assertEquals(USER_EMAIL_EXISTS, exc.getMessage());

    }


}