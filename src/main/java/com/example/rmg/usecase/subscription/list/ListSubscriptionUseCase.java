package com.example.rmg.usecase.subscription.list;

import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.subscription.persistence.SubscriptionPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class ListSubscriptionUseCase implements UseCase<ListSubscriptionUseCaseInput, ListSubscriptionUseCaseOutput> {


    private final SubscriptionPersistence subscriptionPersistence;

    private final UserPersistence userPersistence;

    @Override
    public ListSubscriptionUseCaseOutput execute(ListSubscriptionUseCaseInput input) {

        final User student = userPersistence.get(input.getStudentId());

        final List<Subscription> subscriptions = subscriptionPersistence.findByStudent(student);

        return ListSubscriptionUseCaseOutput.of(subscriptions);
    }
}

