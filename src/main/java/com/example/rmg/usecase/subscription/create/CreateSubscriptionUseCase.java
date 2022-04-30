package com.example.rmg.usecase.subscription.create;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.subscription.persistence.SubscriptionPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateSubscriptionUseCase implements UseCase<CreateSubscriptionUseCaseInput, CreateSubscriptionUseCaseOutput> {

    private final SubscriptionPersistence subscriptionPersistence;

    private final UserPersistence userPersistence;

    private final CoursePersistence coursePersistence;


    @Override
    public CreateSubscriptionUseCaseOutput execute(CreateSubscriptionUseCaseInput input) {

        final User student = userPersistence.get(input.getStudentId());

        input.getCourseIds().forEach((courseId) -> {

            final Course course = coursePersistence.get(courseId);
            final Subscription subscription = Subscription
                    .builder()
                    .student(student)
                    .course(course)
                    .build();
            subscriptionPersistence.save(subscription);
        });

        return CreateSubscriptionUseCaseOutput.builder().build();
    }
}
