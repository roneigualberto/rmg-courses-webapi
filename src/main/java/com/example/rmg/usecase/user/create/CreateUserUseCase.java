package com.example.rmg.usecase.user.create;


import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.messages.UserMessages;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
public class CreateUserUseCase implements UseCase<CreateUserUseCaseInput, CreateUserUseCaseOutput> {


    private final UserPersistence userPersistence;

    @Override
    public CreateUserUseCaseOutput execute(CreateUserUseCaseInput input) {

        validateInput(input);

        User user = User.builder()
                .id(UUID.randomUUID())
                .email(input.getEmail())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .password(input.getPassword())
                .birthDate(input.getBirthDate())
                .build();

        user.valid(); // Valid User

        userPersistence.save(user);

        return CreateUserUseCaseOutput.of(user);

    }

    private void validateInput(CreateUserUseCaseInput input) {

        Optional<User> optUser = userPersistence.findByEmail(input.getEmail());

        if (optUser.isPresent()) {
            throw new DomainException(UserMessages.USER_EMAIL_EXISTS);
        }
    }
}
