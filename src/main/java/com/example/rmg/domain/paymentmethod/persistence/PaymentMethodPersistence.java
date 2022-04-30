package com.example.rmg.domain.paymentmethod.persistence;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.common.persistence.GenericPersistence;
import com.example.rmg.domain.paymentmethod.messages.PaymentMethodMessages;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;

import java.util.List;
import java.util.UUID;

public interface PaymentMethodPersistence extends GenericPersistence<PaymentMethod, UUID> {

    default PaymentMethod get(UUID paymentMethodId) throws DomainException {
        return findById(paymentMethodId).orElseThrow(() -> new DomainException(PaymentMethodMessages.PAYMENT_METHOD_NOT_FOUND));
    }

    List<PaymentMethod> findByOwner(User owner);
}