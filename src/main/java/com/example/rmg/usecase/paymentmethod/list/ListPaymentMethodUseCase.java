package com.example.rmg.usecase.paymentmethod.list;

import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import com.example.rmg.domain.paymentmethod.persistence.PaymentMethodPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class ListPaymentMethodUseCase implements UseCase<ListPaymentMethodUseCaseInput, ListPaymentMethodUseCaseOutput> {


    private final PaymentMethodPersistence paymentMethodPersistence;

    private final UserPersistence userPersistence;

    @Override
    public ListPaymentMethodUseCaseOutput execute(ListPaymentMethodUseCaseInput input) {

        final User owner = userPersistence.get(input.getOwnerId());

        final List<PaymentMethod> paymentMethods = paymentMethodPersistence.findByOwner(owner);

        return ListPaymentMethodUseCaseOutput.of(paymentMethods);
    }
}

