package com.example.rmg.infrastructure.test.builders;

import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntity;
import com.example.rmg.infrastructure.persistence.jpa.paymentmethod.PaymentMethodEntity;
import com.example.rmg.infrastructure.persistence.jpa.purchase.PurchaseEntity;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;

import java.util.Set;
import java.util.UUID;

import static com.example.rmg.infrastructure.test.builders.PaymentMethods.aPaymentMethod;

public class Purchases {

    public static Purchase.PurchaseBuilder aPurchase(User buyer) {

        return Purchase.builder()
                .buyer(buyer)
                .paymentMethod(aPaymentMethod(buyer).build());

    }

    public static PurchaseEntity.PurchaseEntityBuilder aPurchaseEntity(UserEntity buyer,
                                                                 PaymentMethodEntity paymentMethodEntity
    ) {

        return PurchaseEntity.builder()
                .id(UUID.randomUUID())
                .buyer(buyer)
                .paymentMethod(paymentMethodEntity);

    }


}
