package com.example.rmg.infrastructure.persistence.jpa.paymentmethod;

import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import com.example.rmg.domain.paymentmethod.persistence.PaymentMethodPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PaymentMethodJPAPersistence implements PaymentMethodPersistence {


    private final PaymentMethodEntityRepository repository;

    private final PaymentMethodEntityMapper entityMapper;

    @Override
    public void save(PaymentMethod lecture) {
        PaymentMethodEntity entity = entityMapper.toPaymentMethodEntity(lecture);
        repository.save(entity);
    }

    @Override
    public void update(PaymentMethod entity) {

    }

    @Override
    public List<PaymentMethod> findAll() {
        return null;
    }

    @Override
    public Optional<PaymentMethod> findById(UUID paymentMethodId) {
        return repository.findById(paymentMethodId).map(entityMapper::toPaymentMethod);
    }

    @Override
    public void deleteById(UUID paymentMethodId) {
        repository.deleteById(paymentMethodId);
    }

    @Override
    public List<PaymentMethod> findByOwner(User owner) {

        UserEntity userEntity = UserEntity.builder().id(owner.getId()).build();
        return repository
                .findByOwner(userEntity).stream()
                .map(entityMapper::toPaymentMethod).collect(Collectors.toList());
    }
}
