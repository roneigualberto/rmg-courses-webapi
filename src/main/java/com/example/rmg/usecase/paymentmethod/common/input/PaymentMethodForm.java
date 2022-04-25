package com.example.rmg.usecase.paymentmethod.common.input;

import com.example.rmg.domain.paymentmethod.valueobject.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class PaymentMethodForm {

    private Brand brand;

    private String nameOnCard;

    private String cardNumber;

    private Integer expirationMonth;

    private Integer expirationYear;

    private Integer cvv;
}
