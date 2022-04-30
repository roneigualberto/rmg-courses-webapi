package com.example.rmg.usecase.subscription.finish;


import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.subscription.persistence.SubscriptionPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import static com.example.rmg.domain.subscription.messages.SubscriptionMessages.SUBSCRIPTION_DOES_NOT_BELONG_TO_STUDENT;

@RequiredArgsConstructor
public class FinishSubscriptionUseCase implements UseCase<FinishSubscriptionUseCaseInput, FinishSubscriptionUseCaseOutput> {

    private final SubscriptionPersistence subscriptionPersistence;

    private final UserPersistence userPersistence;

    @Override
    public FinishSubscriptionUseCaseOutput execute(FinishSubscriptionUseCaseInput input) {

        final User student = userPersistence.get(input.getStudentId());
        final Subscription subscription = subscriptionPersistence.get(input.getSubscriptionId());

        if (subscription.doNotBelong(student)) {
            throw new DomainException(SUBSCRIPTION_DOES_NOT_BELONG_TO_STUDENT);
        }

        subscription.finish(input.getRating(), input.getComment());

        subscriptionPersistence.update(subscription);

        return FinishSubscriptionUseCaseOutput.builder().build();
    }
}
