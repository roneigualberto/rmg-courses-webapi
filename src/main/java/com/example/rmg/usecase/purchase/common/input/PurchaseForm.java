package com.example.rmg.usecase.purchase.common.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@Data
@Builder
public class PurchaseForm {

    private Set<UUID> coursesId;

    private UUID paymentMethodId;
}
