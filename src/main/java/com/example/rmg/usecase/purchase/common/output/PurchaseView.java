package com.example.rmg.usecase.purchase.common.output;


import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.usecase.paymentmethod.common.output.PaymentMethodView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class PurchaseView {

    private UUID id;

    private UUID buyerId;

    private PaymentMethodView paymentMethod;

    private LocalDateTime createdAt;

    private List<PurchaseItemView> items;

    private Double total;
}
