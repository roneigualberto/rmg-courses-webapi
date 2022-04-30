package com.example.rmg.infrastructure.persistence.jpa.subscription;

import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.purchase.persistence.PurchasePersistence;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.subscription.persistence.SubscriptionPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.infrastructure.persistence.jpa.purchase.PurchaseEntity;
import com.example.rmg.infrastructure.persistence.jpa.purchase.PurchaseEntityMapper;
import com.example.rmg.infrastructure.persistence.jpa.purchase.PurchaseEntityRepository;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubscriptionJPAPersistence implements SubscriptionPersistence {


    private final SubscriptionEntityRepository repository;

    private final SubscriptionEntityMapper entityMapper;


    @Override
    public void save(Subscription subscription) {
        final SubscriptionEntity purchaseEntity = entityMapper.toSubscriptionEntity(subscription);
        repository.save(purchaseEntity);
    }

    @Override
    public void update(Subscription subscription) {
        this.save(subscription);
    }

    @Override
    public List<Subscription> findAll() {
        return repository.findAll().stream()
                .map(entityMapper::toSubscription)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Subscription> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public void deleteById(UUID purchaseId) {
        repository.deleteById(purchaseId);
    }

    @Override
    public List<Subscription> findByStudent(User student) {
        UserEntity userEntity = UserEntity.builder().id(student.getId()).build();
        List<SubscriptionEntity> entities = repository.findByStudent(userEntity);

        return entities.stream()
                .map(entityMapper::toSubscription)
                .collect(Collectors.toList());
    }

}
