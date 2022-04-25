package com.example.rmg.infrastructure.persistence.jpa.paymentmethod;

import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface PaymentMethodEntityRepository extends JpaRepository<PaymentMethodEntity, UUID> {


    List<PaymentMethodEntity> findByOwner(UserEntity userEntity);
}
