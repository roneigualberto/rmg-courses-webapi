package com.example.rmg.application.events.purchase;

import com.example.rmg.domain.purchase.event.PurchaseCreatedEvent;
import com.example.rmg.domain.purchase.event.PurchaseCreatedEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseCreatedEventPublisher implements PurchaseCreatedEventHandler {


    private final ApplicationEventPublisher publisher;


    @Override
    public void handler(PurchaseCreatedEvent event) {
        publisher.publishEvent(event);
    }
}
