package com.example.rmg.infrastructure.persistence.jpa.user;

import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserJPAPersistence implements UserPersistence {


    private final UserEntityRepository repository;

    private final UserEntityMapper userEntityMapper;


    @Override
    public void save(User user) {
        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        repository.save(userEntity);
    }

    @Override
    public void update(User entity) {
        this.save(entity);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream().map(userEntityMapper::toUser).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return repository.findById(userId).map(userEntityMapper::toUser);
    }

    @Override
    public void deleteById(UUID userId) {
        this.repository.deleteById(userId);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(userEntityMapper::toUser);
    }
}
