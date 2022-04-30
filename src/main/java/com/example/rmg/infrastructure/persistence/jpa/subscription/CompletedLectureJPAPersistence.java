package com.example.rmg.infrastructure.persistence.jpa.subscription;

import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.subscription.entity.CompletedLecture;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.subscription.persistence.CompletedLecturePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CompletedLectureJPAPersistence implements CompletedLecturePersistence {


    private final CompletedLectureEntityRepository repository;

    private final SubscriptionEntityMapper entityMapper;

    @Override
    public void save(CompletedLecture completedLecture) {

        CompletedLectureEntity entity = entityMapper.toCompletedLectureEntity(completedLecture);
        repository.save(entity);
    }

    @Override
    public void update(CompletedLecture entity) {
        this.save(entity);
    }

    @Override
    public List<CompletedLecture> findAll() {
        return null;
    }

    @Override
    public Optional<CompletedLecture> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public boolean isCompleted(Subscription subscription,Lecture lecture) {
        return repository.existsBySubscriptionIdAndLectureId(subscription.getId(), lecture.getId());
    }
}
