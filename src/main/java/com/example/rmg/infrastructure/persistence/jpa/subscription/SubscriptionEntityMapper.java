package com.example.rmg.infrastructure.persistence.jpa.subscription;


import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.purchase.entity.PurchaseItem;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.infrastructure.persistence.jpa.purchase.PurchaseEntity;
import com.example.rmg.infrastructure.persistence.jpa.purchase.PurchaseItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionEntityMapper {

    SubscriptionEntity toSubscriptionEntity(Subscription subscription);

    Subscription toSubscription(SubscriptionEntity subscriptionEntity);

}
