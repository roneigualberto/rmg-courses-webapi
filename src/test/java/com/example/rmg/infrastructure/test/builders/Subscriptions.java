package com.example.rmg.infrastructure.test.builders;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntity;
import com.example.rmg.infrastructure.persistence.jpa.lecture.LectureEntity;
import com.example.rmg.infrastructure.persistence.jpa.subscription.CompletedLectureEntity;
import com.example.rmg.infrastructure.persistence.jpa.subscription.SubscriptionEntity;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Subscriptions {

    public static Subscription.SubscriptionBuilder aSubscription(User student, Course course) {

        return Subscription.builder()
                .createdAt(LocalDateTime.now())
                .student(student)
                .course(course);


    }

    public static SubscriptionEntity.SubscriptionEntityBuilder aSubscriptionEntity(UserEntity student, CourseEntity course) {

        return SubscriptionEntity.builder()
                .id(UUID.randomUUID())
                .createdAt(LocalDateTime.now())
                .student(student)
                .course(course);


    }

    public static CompletedLectureEntity.CompletedLectureEntityBuilder aCompletedLectureEntity(SubscriptionEntity subscriptionEntity, LectureEntity lectureEntity) {

        return CompletedLectureEntity.builder()
                .id(UUID.randomUUID())
                .completedAt(LocalDateTime.now())
                .subscription(subscriptionEntity)
                .lecture(lectureEntity);


    }
}
