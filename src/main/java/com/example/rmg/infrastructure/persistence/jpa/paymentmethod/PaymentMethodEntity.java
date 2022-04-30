package com.example.rmg.infrastructure.persistence.jpa.paymentmethod;


import com.example.rmg.domain.paymentmethod.valueobject.Brand;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "payment_method")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodEntity {


    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
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
