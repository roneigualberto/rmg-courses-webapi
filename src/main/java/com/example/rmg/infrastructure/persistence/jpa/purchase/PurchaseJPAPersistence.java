package com.example.rmg.infrastructure.persistence.jpa.purchase;

import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.purchase.persistence.PurchasePersistence;
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
public class PurchaseJPAPersistence implements PurchasePersistence {


    private final PurchaseEntityRepository repository;

    private final PurchaseEntityMapper entityMapper;


    @Override
    public void save(Purchase purchase) {
        final PurchaseEntity purchaseEntity = entityMapper.toPurchaseEntityWithItems(purchase);
        repository.save(purchaseEntity);
    }

    @Override
    public void update(Purchase purchase) {
        this.save(purchase);
    }

    @Override
    public List<Purchase> findAll() {
        return repository.findAll().stream()
                .map(entityMapper::toPurchaseWithItems)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Purchase> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public void deleteById(UUID purchaseId) {
        repository.deleteById(purchaseId);
    }

    @Override
    public List<Purchase> findByBuyer(User buyer) {
        UserEntity buyerEntity = UserEntity.builder().id(buyer.getId()).build();
        List<PurchaseEntity> entities = repository.findByBuyer(buyerEntity);
        return entities.stream()
                .map(entityMapper::toPurchaseWithItems)
                .collect(Collectors.toList());
    }


}
