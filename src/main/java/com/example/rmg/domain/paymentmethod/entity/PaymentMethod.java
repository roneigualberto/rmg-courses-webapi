package com.example.rmg.domain.paymentmethod.entity;


import com.example.rmg.domain.common.validator.ValidatorUtil;
import com.example.rmg.domain.paymentmethod.valueobject.Brand;
import com.example.rmg.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Getter
@Builder
public class PaymentMethod {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotNull
    private User owner;

    @NotNull
    private Brand brand;

    @NotBlank
    private String nameOnCard;

    @NotBlank
    private String cardNumber;

    @NotNull
    private Integer expirationMonth;

    @NotNull
    private Integer expirationYear;

    @NotNull
    private Integer cvv;

    public void valid() {
        ValidatorUtil.validate(this);
    }
}
