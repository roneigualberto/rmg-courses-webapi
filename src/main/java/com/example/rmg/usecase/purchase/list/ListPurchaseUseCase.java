package com.example.rmg.usecase.purchase.list;

import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.purchase.persistence.PurchasePersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class ListPurchaseUseCase implements UseCase<ListPurchaseUseCaseInput, ListPurchaseUseCaseOutput> {


    private final PurchasePersistence purchasePersistence;

    private final UserPersistence userPersistence;

    @Override
    public ListPurchaseUseCaseOutput execute(ListPurchaseUseCaseInput input) {

        final User buyer = userPersistence.get(input.getBuyerId());

        final List<Purchase> purchases = purchasePersistence.findByBuyer(buyer);

        return ListPurchaseUseCaseOutput.of(purchases);
    }
}

