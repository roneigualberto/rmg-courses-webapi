package com.example.rmg.usecase.purchase.create;


import com.example.rmg.usecase.purchase.common.input.PurchaseForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class CreatePurchaseUseCaseInput {

    private UUID buyerId;

    private UUID paymentMethodId;

    private PurchaseForm purchase;
}
