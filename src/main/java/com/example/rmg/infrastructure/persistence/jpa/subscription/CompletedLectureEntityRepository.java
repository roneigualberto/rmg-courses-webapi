package com.example.rmg.infrastructure.persistence.jpa.subscription;

import com.example.rmg.domain.subscription.entity.CompletedLecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompletedLectureEntityRepository extends JpaRepository<CompletedLectureEntity, UUID> {

    boolean existsBySubscriptionIdAndLectureId(UUID subscriptionId, UUID lectureId);

    Optional<CompletedLectureEntity> findBySubscriptionIdAndLectureId(UUID id, UUID id1);
}
