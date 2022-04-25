package com.example.rmg.application.rest.user;

import com.example.rmg.domain.paymentmethod.valueobject.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
