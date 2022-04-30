package com.example.rmg.infrastructure.test.builders;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.infrastructure.persistence.jpa.paymentmethod.PaymentMethodEntity;
import com.example.rmg.infrastructure.persistence.jpa.purchase.PurchaseEntity;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.rmg.infrastructure.test.builders.PaymentMethods.aPaymentMethod;

public class Subscriptions {

    public static Subscription.SubscriptionBuilder aSubscription(User student, Course course) {

        return Subscription.builder()
                .createdAt(LocalDateTime.now())
                .student(student)
                .course(course);


    }
}
