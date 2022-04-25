package com.example.rmg.domain.paymentmethod.persistence;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.common.persistence.GenericPersistence;
import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;

import java.util.UUID;

import static com.example.rmg.domain.paymentmethod.messages.PaymentMethodMessages.PAYMENT_METHOD_NOT_FOUND;

public interface PaymentMethodPersistence extends GenericPersistence<PaymentMethod, UUID> {

    default PaymentMethod get(UUID paymentMethodId) throws DomainException {
        return findById(paymentMethodId).orElseThrow(() -> new DomainException(PAYMENT_METHOD_NOT_FOUND));
    }
}
