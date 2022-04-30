package com.example.rmg.infrastructure.application.webapi.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class PurchaseResponse {

    private UUID id;

    private UUID buyerId;

    private PaymentMethodResponse paymentMethod;

    private LocalDateTime createdAt;

    private List<PurchaseItemResponse> items;

    private Double total;
}