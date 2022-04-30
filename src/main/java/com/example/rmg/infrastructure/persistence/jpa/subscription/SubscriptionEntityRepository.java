package com.example.rmg.infrastructure.persistence.jpa.subscription;

import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubscriptionEntityRepository extends JpaRepository<SubscriptionEntity, UUID> {


    List<SubscriptionEntity> findByStudent(UserEntity buyerEntity);
}
