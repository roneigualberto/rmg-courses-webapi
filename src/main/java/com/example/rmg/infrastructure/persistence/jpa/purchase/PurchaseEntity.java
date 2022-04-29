package com.example.rmg.infrastructure.persistence.jpa.purchase;


import com.example.rmg.infrastructure.persistence.jpa.paymentmethod.PaymentMethodEntity;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "purchase")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PurchaseEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity buyer;

    @Builder.Default
    @Column(nullable = false)
    private Double total = 0.0;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentMethodEntity paymentMethod;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PurchaseItemEntity> items = new ArrayList<>();

}
