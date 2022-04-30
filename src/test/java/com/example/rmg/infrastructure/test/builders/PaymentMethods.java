package com.example.rmg.infrastructure.test.builders;

import com.example.rmg.application.rest.user.PaymentMethodRequest;
import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import com.example.rmg.domain.paymentmethod.valueobject.Brand;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.infrastructure.persistence.jpa.paymentmethod.PaymentMethodEntity;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import com.example.rmg.usecase.paymentmethod.common.input.PaymentMethodForm;

import java.time.Month;
import java.util.UUID;

public class PaymentMethods {


    public static final String CARD_NUMBER = "5574 2549 1939 2751";
    public static final int EXPIRATION_MONTH = Month.APRIL.getValue();
    public static final int EXPIRATION_YEAR = 2030;
    public static final Brand BRAND = Brand.MASTERCARD;
    public static final String NAME_ON_CARD = "User name";
    public static final int CVV = 671;

    public static PaymentMethodForm.PaymentMethodFormBuilder aPaymentMethodForm() {
        return PaymentMethodForm.builder()
                .brand(BRAND)
                .cardNumber(CARD_NUMBER)
                .expirationMonth(EXPIRATION_MONTH)
                .expirationYear(EXPIRATION_YEAR)
                .cvv(CVV)
                .nameOnCard(NAME_ON_CARD);
    }

    public static PaymentMethodRequest.PaymentMethodRequestBuilder aPaymentRequest() {
        return PaymentMethodRequest.builder()
                .brand(BRAND)
                .cardNumber(CARD_NUMBER)
                .expirationMonth(EXPIRATION_MONTH)
                .expirationYear(EXPIRATION_YEAR)
                .cvv(CVV)
                .nameOnCard(NAME_ON_CARD);
    }

    public static PaymentMethod.PaymentMethodBuilder aPaymentMethod(User owner) {
        return PaymentMethod.builder()
                .owner(owner)
                .brand(BRAND)
                .cardNumber(CARD_NUMBER)
                .expirationMonth(EXPIRATION_MONTH)
                .expirationYear(EXPIRATION_YEAR)
                .cvv(CVV)
                .nameOnCard(NAME_ON_CARD);
    }

    public static PaymentMethodEntity.PaymentMethodEntityBuilder aPaymentMethodEntity(UserEntity owner) {
        return PaymentMethodEntity.builder()
                .id(UUID.randomUUID())
                .owner(owner)
                .brand(BRAND)
                .cardNumber(CARD_NUMBER)
                .expirationMonth(EXPIRATION_MONTH)
                .expirationYear(EXPIRATION_YEAR)
                .cvv(CVV)
                .nameOnCard(NAME_ON_CARD);
    }


}
