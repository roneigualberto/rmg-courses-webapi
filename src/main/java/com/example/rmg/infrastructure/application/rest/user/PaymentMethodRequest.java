package com.example.rmg.infrastructure.application.rest.user;

import com.example.rmg.domain.paymentmethod.valueobject.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PaymentMethodRequest {

    private Brand brand;

    private String nameOnCard;

    private String cardNumber;

    private Integer expirationMonth;

    private Integer expirationYear;

    private Integer cvv;
}
