package com.example.rmg.usecase.purchase.create;


import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.usecase.purchase.common.input.PurchaseForm;
import com.example.rmg.usecase.purchase.common.mappers.PurchaseMapper;
import com.example.rmg.usecase.purchase.common.output.PurchaseView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CreatePurchaseUseCaseOutput {

    private PurchaseView purchase;

    public static CreatePurchaseUseCaseOutput of(Purchase purchase) {
        return CreatePurchaseUseCaseOutput.builder()
                .purchase(PurchaseMapper.INSTANCE.toPurchaseView(purchase))
                .build();
    }
}
