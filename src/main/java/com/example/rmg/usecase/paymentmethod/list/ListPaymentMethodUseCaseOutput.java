package com.example.rmg.usecase.paymentmethod.list;

import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import com.example.rmg.usecase.paymentmethod.common.mappers.PaymentMethodMapper;
import com.example.rmg.usecase.paymentmethod.common.output.PaymentMethodView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Data
@Builder
public class ListPaymentMethodUseCaseOutput {

    private List<PaymentMethodView> paymentMethods;

    public static ListPaymentMethodUseCaseOutput of(List<PaymentMethod> paymentMethods) {

        final List<PaymentMethodView> paymentMethodViews = paymentMethods
                .stream()
                .map(PaymentMethodMapper.INSTANCE::toPaymentMethodView)
                .collect(Collectors.toList());

        return ListPaymentMethodUseCaseOutput.builder()
                .paymentMethods(paymentMethodViews)
                .build();
    }
}
