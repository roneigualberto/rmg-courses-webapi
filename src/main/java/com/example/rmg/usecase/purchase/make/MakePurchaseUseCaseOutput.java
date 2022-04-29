package com.example.rmg.usecase.purchase.make;


import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.usecase.purchase.common.mappers.PurchaseMapper;
import com.example.rmg.usecase.purchase.common.output.PurchaseView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MakePurchaseUseCaseOutput {

    private PurchaseView purchase;

    public static MakePurchaseUseCaseOutput of(Purchase purchase) {
        return MakePurchaseUseCaseOutput.builder()
                .purchase(PurchaseMapper.INSTANCE.toPurchaseView(purchase))
                .build();
    }
}
