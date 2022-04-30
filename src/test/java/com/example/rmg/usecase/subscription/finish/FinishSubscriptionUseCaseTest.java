package com.example.rmg.usecase.subscription.finish;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.subscription.persistence.SubscriptionPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.subscription.completelecture.CompleteLectureUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.rmg.infrastructure.test.builders.Courses.aCourse;
import static com.example.rmg.infrastructure.test.builders.Lectures.aLecture;
import static com.example.rmg.infrastructure.test.builders.Subscriptions.aSubscription;
import static com.example.rmg.infrastructure.test.builders.Users.anUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinishSubscriptionUseCaseTest {


    @InjectMocks
    private FinishSubscriptionUseCase useCase;

    @Mock
    private SubscriptionPersistence subscriptionPersistence;


    @Mock
    private UserPersistence userPersistence;

    @Test
    void should_finish_subscription() {

        final Course course = aCourse().build();

        final User student = anUser().build();
        when(userPersistence.get(any())).thenReturn(student);

        final Subscription subscription = aSubscription(student, course).build();
        when(subscriptionPersistence.get(any())).thenReturn(subscription);


        FinishSubscriptionUseCaseInput input = FinishSubscriptionUseCaseInput.builder()
                .studentId(student.getId())
                .subscriptionId(subscription.getId())
                .rating(10)
                .comment("Course awesome!")
                .build();

        FinishSubscriptionUseCaseOutput output = useCase.execute(input);

        verify(subscriptionPersistence).update(any());

    }


}