package com.example.rmg.infrastructure.persistence.jpa.purchase;

import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchaseEntityRepository extends JpaRepository<PurchaseEntity, UUID> {


    List<PurchaseEntity> findByBuyer(UserEntity buyerEntity);
}
