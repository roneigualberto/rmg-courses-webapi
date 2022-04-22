package com.example.rmg.application.rest.user;

import com.example.rmg.usecase.user.create.CreateUserUseCaseInput;
import com.example.rmg.usecase.user.create.common.output.UserView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserUseCaseInput toCreateUserUseCaseInput(UserRequest request);

    UserResponse toUserResponse(UserView userView);

}
