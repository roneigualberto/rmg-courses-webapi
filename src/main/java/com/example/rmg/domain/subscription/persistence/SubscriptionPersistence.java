package com.example.rmg.domain.subscription.persistence;

import com.example.rmg.domain.common.persistence.GenericPersistence;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.subscription.messages.SubscriptionMessages;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface SubscriptionPersistence extends GenericPersistence<Subscription, UUID> {

    default Subscription get(UUID subscriptionId) throws DomainException {
        return findById(subscriptionId).orElseThrow(() -> new DomainException(SubscriptionMessages.SUBSCRIPTION_NOT_FOUND));
    }

    List<Subscription> findByStudent(User student);
}