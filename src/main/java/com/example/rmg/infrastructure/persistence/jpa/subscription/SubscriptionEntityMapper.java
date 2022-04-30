package com.example.rmg.infrastructure.persistence.jpa.subscription;


import com.example.rmg.domain.subscription.entity.CompletedLecture;
import com.example.rmg.domain.subscription.entity.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionEntityMapper {

    SubscriptionEntity toSubscriptionEntity(Subscription subscription);

    Subscription toSubscription(SubscriptionEntity subscriptionEntity);


    CompletedLectureEntity toCompletedLectureEntity(CompletedLecture completedLecture);

    CompletedLecture toCompletedLecture(CompletedLectureEntity completedLectureEntity);

}
