package com.example.rmg.usecase.paymentmethod.list;

import com.example.rmg.usecase.paymentmethod.common.input.PaymentMethodForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class ListPaymentMethodUseCaseInput {

    private UUID ownerId;

}
