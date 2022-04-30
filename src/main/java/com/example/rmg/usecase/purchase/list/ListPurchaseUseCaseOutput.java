package com.example.rmg.usecase.purchase.list;

import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.usecase.purchase.common.mappers.PurchaseMapper;
import com.example.rmg.usecase.purchase.common.output.PurchaseView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Data
@Builder
public class ListPurchaseUseCaseOutput {

    private List<PurchaseView> purchases;

    public static ListPurchaseUseCaseOutput of(List<Purchase> purchases) {

        final List<PurchaseView> purchaseViews = purchases
                .stream()
                .map(PurchaseMapper.INSTANCE::toPurchaseView)
                .collect(Collectors.toList());

        return ListPurchaseUseCaseOutput.builder()
                .purchases(purchaseViews)
                .build();
    }
}
