package com.example.rmg.domain.subscription.persistence;

import com.example.rmg.domain.common.persistence.GenericPersistence;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.subscription.entity.CompletedLecture;
import com.example.rmg.domain.subscription.entity.Subscription;

import java.util.Optional;
import java.util.UUID;

public interface CompletedLecturePersistence extends GenericPersistence<CompletedLecture, UUID> {


    boolean isCompleted(Subscription subscription, Lecture lecture);

    Optional<CompletedLecture> findBySubscriptionAndLecture(Subscription subscription, Lecture lecture);

}