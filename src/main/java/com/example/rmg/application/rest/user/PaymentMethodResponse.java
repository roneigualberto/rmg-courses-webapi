package com.example.rmg.application.rest.user;

import com.example.rmg.domain.paymentmethod.valueobject.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PaymentMethodResponse {


    private UUID id;

    private UUID ownerId;

    private Brand brand;

    private String nameOnCard;

    private String cardNumber;

    private Integer expirationMonth;

    private Integer expirationYear;

}
