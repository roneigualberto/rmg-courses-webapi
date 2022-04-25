package com.example.rmg.infrastructure.persistence.jpa.paymentmethod;


import com.example.rmg.domain.common.validator.ValidatorUtil;
import com.example.rmg.domain.paymentmethod.valueobject.Brand;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "payment_method")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodEntity {


    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity owner;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Brand brand;

    @Column(nullable = false)
    private String nameOnCard;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private Integer expirationMonth;

    @Column(nullable = false)
    private Integer expirationYear;

    @Column(nullable = false)
    private Integer cvv;

}
