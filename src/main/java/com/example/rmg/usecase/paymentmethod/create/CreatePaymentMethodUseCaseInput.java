package com.example.rmg.usecase.paymentmethod.create;

import com.example.rmg.usecase.paymentmethod.common.input.PaymentMethodForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class CreatePaymentMethodUseCaseInput {


    private UUID ownerId;

    private PaymentMethodForm paymentMethod;


}
