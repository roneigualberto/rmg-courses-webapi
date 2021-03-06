package com.example.rmg.domain.purchase.persistence;

import com.example.rmg.domain.common.persistence.GenericPersistence;
import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.purchase.messages.PurchaseMessages;
import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface PurchasePersistence extends GenericPersistence<Purchase, UUID> {

    default Purchase get(UUID purchaseId) throws DomainException {
        return findById(purchaseId).orElseThrow(() -> new DomainException(PurchaseMessages.PURCHASE_NOT_FOUND));
    }

    List<Purchase> findByBuyer(User buyer);
}