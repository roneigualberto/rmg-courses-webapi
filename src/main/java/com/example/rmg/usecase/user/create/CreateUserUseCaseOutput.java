package com.example.rmg.usecase.user.create;

import com.example.rmg.domain.user.entity.User;
import com.example.rmg.usecase.user.create.common.output.UserView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class CreateUserUseCaseOutput {

    private UserView user;


    public static CreateUserUseCaseOutput of(User user) {

        return CreateUserUseCaseOutput.builder()
                .user(UserView.of(user))
                .build();

    }


}
