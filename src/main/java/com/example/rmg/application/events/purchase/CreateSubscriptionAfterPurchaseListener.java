package com.example.rmg.application.events.purchase;

import com.example.rmg.domain.purchase.event.PurchaseCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateSubscriptionAfterPurchaseListener {


    @Transactional
    @EventListener
    @Async
    public void listener(PurchaseCreatedEvent event) {
        System.out.println("Create Subscription  " + event);
    }
}
