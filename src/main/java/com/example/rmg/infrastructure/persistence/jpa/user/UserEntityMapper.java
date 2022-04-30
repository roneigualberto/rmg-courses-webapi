package com.example.rmg.infrastructure.persistence.jpa.user;


import com.example.rmg.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {


    UserEntity toUserEntity(User user);

    User toUser(UserEntity entity);
}
