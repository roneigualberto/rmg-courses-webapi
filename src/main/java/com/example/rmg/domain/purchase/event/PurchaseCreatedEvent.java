package com.example.rmg.domain.purchase.event;

import com.example.rmg.usecase.purchase.common.output.PurchaseView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PurchaseCreatedEvent {

    private final PurchaseView purchase;
}
