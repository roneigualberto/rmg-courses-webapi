package com.example.rmg.usecase.paymentmethod.create;

import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import com.example.rmg.usecase.paymentmethod.common.mappers.PaymentMethodMapper;
import com.example.rmg.usecase.paymentmethod.common.output.PaymentMethodView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class CreatePaymentMethodUseCaseOutput {


    private PaymentMethodView paymentMethod;

    public static CreatePaymentMethodUseCaseOutput of(PaymentMethod paymentMethod) {

        return CreatePaymentMethodUseCaseOutput.builder()
                .paymentMethod(PaymentMethodMapper.INSTANCE.toPaymentMethodView(paymentMethod))
                .build();
    }
}
