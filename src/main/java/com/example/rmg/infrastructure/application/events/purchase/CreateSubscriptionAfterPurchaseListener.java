package com.example.rmg.infrastructure.application.events.purchase;

import com.example.rmg.domain.purchase.event.PurchaseCreatedEvent;
import com.example.rmg.usecase.purchase.common.output.PurchaseView;
import com.example.rmg.usecase.subscription.create.CreateSubscriptionUseCase;
import com.example.rmg.usecase.subscription.create.CreateSubscriptionUseCaseInput;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CreateSubscriptionAfterPurchaseListener {


    private final CreateSubscriptionUseCase createSubscriptionUseCase;


    @Transactional
    @EventListener
    @Async
    public void listener(PurchaseCreatedEvent event) {

        final PurchaseView purchase = event.getPurchase();

        final Set<UUID> courseIds = purchase.getItems().stream().map((item) -> item.getCourse().getId()).collect(Collectors.toSet());


        final CreateSubscriptionUseCaseInput input = CreateSubscriptionUseCaseInput.builder()
                .studentId(purchase.getBuyerId())
                .courseIds(courseIds)
                .build();

        createSubscriptionUseCase.execute(input);
    }
}
