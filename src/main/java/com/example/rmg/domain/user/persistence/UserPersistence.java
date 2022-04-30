package com.example.rmg.domain.user.persistence;

import com.example.rmg.domain.common.persistence.GenericPersistence;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.messages.UserMessages;

import java.util.Optional;
import java.util.UUID;

public interface UserPersistence extends GenericPersistence<User, UUID> {

    default User get(UUID userId) throws DomainException {
        return findById(userId).orElseThrow(() -> new DomainException(UserMessages.USER_NOT_FOUND));
    }

    Optional<User> findByEmail(String email);
}
