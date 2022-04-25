package com.example.rmg.usecase.paymentmethod.common.output;

import com.example.rmg.domain.paymentmethod.valueobject.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
public class PaymentMethodView {

    private UUID id;

    private UUID ownerId;

    private Brand brand;

    private String nameOnCard;

    private String cardNumber;

    private Integer expirationMonth;

    private Integer expirationYear;
}
