package com.example.rmg.usecase.paymentmethod.create;

import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import com.example.rmg.domain.paymentmethod.persistence.PaymentMethodPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.common.UseCase;
import com.example.rmg.usecase.paymentmethod.common.input.PaymentMethodForm;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CreatePaymentMethodUseCase implements UseCase<CreatePaymentMethodUseCaseInput, CreatePaymentMethodUseCaseOutput> {


    private final PaymentMethodPersistence paymentMethodPersistence;

    private final UserPersistence userPersistence;

    @Override
    public CreatePaymentMethodUseCaseOutput execute(CreatePaymentMethodUseCaseInput input) {

        PaymentMethodForm form = input.getPaymentMethod();


        User owner = userPersistence.get(input.getOwnerId());

        PaymentMethod paymentMethod = PaymentMethod.builder()
                .brand(form.getBrand())
                .owner(owner)
                .nameOnCard(form.getNameOnCard())
                .cardNumber(form.getCardNumber())
                .cvv(form.getCvv())
                .expirationYear(form.getExpirationYear())
                .expirationMonth(form.getExpirationMonth())
                .build();

        paymentMethod.valid();

        paymentMethodPersistence.save(paymentMethod);

        return CreatePaymentMethodUseCaseOutput.of(paymentMethod);
    }
}

