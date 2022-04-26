package com.example.rmg.usecase.purchase.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class ListPurchaseUseCaseInput {

    private UUID buyerId;

}
