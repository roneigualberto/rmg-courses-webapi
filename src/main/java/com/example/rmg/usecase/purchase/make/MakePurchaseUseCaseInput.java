package com.example.rmg.usecase.purchase.make;


import com.example.rmg.usecase.purchase.common.input.PurchaseForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class MakePurchaseUseCaseInput {

    private UUID buyerId;

    private PurchaseForm purchase;
}
