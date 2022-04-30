package com.example.rmg.usecase.subscription.list;

import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.usecase.subscription.common.mappers.SubscriptionMapper;
import com.example.rmg.usecase.subscription.common.output.SubscriptionView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Data
@Builder
public class ListSubscriptionUseCaseOutput {

    private List<SubscriptionView> subscriptions;

    public static ListSubscriptionUseCaseOutput of(List<Subscription> subscriptions) {

        final List<SubscriptionView> subscriptionViews = subscriptions
                .stream()
                .map(SubscriptionMapper.INSTANCE::toSubscriptionView)
                .collect(Collectors.toList());

        return ListSubscriptionUseCaseOutput.builder()
                .subscriptions(subscriptionViews)
                .build();
    }
}
