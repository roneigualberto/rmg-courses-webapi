package com.example.rmg.infrastructure.persistence.jpa.subscription;


import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntity;
import com.example.rmg.infrastructure.persistence.jpa.paymentmethod.PaymentMethodEntity;
import com.example.rmg.infrastructure.persistence.jpa.purchase.PurchaseItemEntity;
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
@Table(name = "subscription")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class SubscriptionEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity student;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CourseEntity course;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
