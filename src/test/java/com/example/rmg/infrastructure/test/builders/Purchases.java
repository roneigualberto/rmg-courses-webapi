package com.example.rmg.infrastructure.test.builders;

import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.user.entity.User;

import static com.example.rmg.infrastructure.test.builders.PaymentMethods.aPaymentMethod;

public class Purchases {

    public static Purchase.PurchaseBuilder aPurchase(User buyer) {

        return Purchase.builder()
                .buyer(buyer)
                .paymentMethod(aPaymentMethod(buyer).build());

    }


}
