package com.example.rmg.usecase.subscription.list;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.subscription.persistence.SubscriptionPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.subscription.common.output.SubscriptionView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.rmg.infrastructure.test.builders.Courses.aCourse;
import static com.example.rmg.infrastructure.test.builders.Subscriptions.aSubscription;
import static com.example.rmg.infrastructure.test.builders.Users.anUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListSubscriptionUseCaseTest {

    @InjectMocks
    private ListSubscriptionUseCase useCase;

    @Mock
    private SubscriptionPersistence subscriptionPersistence;

    @Mock
    private UserPersistence userPersistence;


    @Test
    void should_list_subscriptions() {

        User student = anUser().build();

        when(userPersistence.get(any())).thenReturn(student);

        Course course1 = aCourse().price(10.20).build();
        Subscription subscription1 = aSubscription(student, course1).build();


        Course course2 = aCourse().price(22.20).build();
        Subscription subscription2 = aSubscription(student, course2).build();


        Course course3 = aCourse().price(11.25).build();
        Subscription subscription3 = aSubscription(student, course3).build();


        List<Subscription> subscriptions = List.of(subscription1, subscription2, subscription3);

        when(subscriptionPersistence.findByStudent(any())).thenReturn(subscriptions);

        ListSubscriptionUseCaseInput input = ListSubscriptionUseCaseInput.builder()
                .studentId(student.getId())
                .build();

        ListSubscriptionUseCaseOutput output = useCase.execute(input);


        for (int i = 0; i < output.getSubscriptions().size(); i++) {
            SubscriptionView result = output.getSubscriptions().get(i);
            Subscription expected = subscriptions.get(i);
            assertEquals(expected.getStudent().getId(), result.getStudentId());
            assertEquals(expected.getCourse().getId(), result.getCourse().getId());
        }

    }

}