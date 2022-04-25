package com.example.rmg.infrastructure.test.builders;

import com.example.rmg.application.rest.course.LectureRequest;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.valueobject.LectureType;
import com.example.rmg.domain.paymentmethod.valueobject.Brand;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntity;
import com.example.rmg.infrastructure.persistence.jpa.lecture.LectureEntity;
import com.example.rmg.usecase.course.lecture.common.input.LectureForm;
import com.example.rmg.usecase.paymentmethod.common.input.PaymentMethodForm;

import java.time.Month;
import java.util.UUID;

public class PaymentMethods {


    public static final String CARD_NUMBER = "5574 2549 1939 2751";
    public static final int EXPIRATION_MONTH = Month.APRIL.getValue();
    public static final int EXPIRATION_YEAR = 2030;
    public static final Brand BRAND = Brand.MASTERCARD;
    public static final String NAME_ON_CARD = "User name";

    public static PaymentMethodForm.PaymentMethodFormBuilder aPaymentMethodForm() {
        return PaymentMethodForm.builder()
                .brand(BRAND)
                .cardNumber(CARD_NUMBER)
                .expirationMonth(EXPIRATION_MONTH)
                .expirationYear(EXPIRATION_YEAR)
                .cvv(671)
                .nameOnCard(NAME_ON_CARD);
    }


}
