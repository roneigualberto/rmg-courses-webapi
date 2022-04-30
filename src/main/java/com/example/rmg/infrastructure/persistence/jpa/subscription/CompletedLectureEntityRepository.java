package com.example.rmg.infrastructure.persistence.jpa.subscription;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompletedLectureEntityRepository extends JpaRepository<CompletedLectureEntity, UUID> {

    boolean existsBySubscriptionIdAndLectureId(UUID subscriptionId, UUID lectureId);
}
