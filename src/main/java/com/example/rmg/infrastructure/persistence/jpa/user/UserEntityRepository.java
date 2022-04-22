package com.example.rmg.infrastructure.persistence.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {


    Optional<UserEntity> findByEmail(String email);
}
