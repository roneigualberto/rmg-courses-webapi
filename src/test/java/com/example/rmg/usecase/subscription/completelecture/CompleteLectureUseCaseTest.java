package com.example.rmg.usecase.subscription.completelecture;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.messages.CourseMessages;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.subscription.messages.SubscriptionMessages;
import com.example.rmg.domain.subscription.persistence.CompletedLecturePersistence;
import com.example.rmg.domain.subscription.persistence.SubscriptionPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.rmg.domain.subscription.messages.SubscriptionMessages.LECTURE_WAS_COMPLETED;
import static com.example.rmg.domain.subscription.messages.SubscriptionMessages.SUBSCRIPTION_DOES_NOT_BELONG_TO_STUDENT;
import static com.example.rmg.infrastructure.test.builders.Courses.aCourse;
import static com.example.rmg.infrastructure.test.builders.Lectures.aLecture;
import static com.example.rmg.infrastructure.test.builders.Subscriptions.aSubscription;
import static com.example.rmg.infrastructure.test.builders.Users.anUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompleteLectureUseCaseTest {

    @InjectMocks
    private CompleteLectureUseCase useCase;

    @Mock
    private SubscriptionPersistence subscriptionPersistence;

    @Mock
    private CompletedLecturePersistence completedLecturePersistence;

    @Mock
    private LecturePersistence lecturePersistence;


    @Mock
    private UserPersistence userPersistence;

    @Test
    void should_complete_lecture() {


        final Course course = aCourse().build();

        final User student = anUser().build();
        when(userPersistence.get(any())).thenReturn(student);


        final Lecture lecture = aLecture(course).build();
        when(lecturePersistence.get(any())).thenReturn(lecture);

        final Subscription subscription = aSubscription(student, course).build();
        when(subscriptionPersistence.get(any())).thenReturn(subscription);

        CompleteLectureUseCaseInput input = CompleteLectureUseCaseInput.builder()
                .subscriptionId(subscription.getId())
                .lectureId(lecture.getId()).build();

        CompleteLectureUseCaseOutput output = useCase.execute(input);
        verify(completedLecturePersistence).save(any());
    }

    @Test
    void should_not_complete_lecture_with_different_courses() {

        final User student = anUser().build();
        final Course course1 = aCourse().build();


        final Lecture lecture = aLecture(course1).build();
        when(lecturePersistence.get(any())).thenReturn(lecture);

        final Course course2 = aCourse().name("Other Course").build();
        final Subscription subscription = aSubscription(student, course2).build();
        when(subscriptionPersistence.get(any())).thenReturn(subscription);

        CompleteLectureUseCaseInput input = CompleteLectureUseCaseInput.builder()
                .subscriptionId(subscription.getId())
                .lectureId(lecture.getId()).build();

        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));

        assertEquals(CourseMessages.LECTURE_DOES_NOT_BELONGS_TO_COURSE, exc.getMessage());
    }

    @Test
    void should_not_complete_lecture_with_lecture_was_completed() {

        final User student = anUser().build();
        final Course course = aCourse().build();


        final Lecture lecture = aLecture(course).build();
        when(lecturePersistence.get(any())).thenReturn(lecture);

        final Subscription subscription = aSubscription(student, course).build();
        when(subscriptionPersistence.get(any())).thenReturn(subscription);
        when(completedLecturePersistence.isCompleted(any(), any())).thenReturn(true);


        CompleteLectureUseCaseInput input = CompleteLectureUseCaseInput.builder()
                .subscriptionId(subscription.getId())
                .lectureId(lecture.getId()).build();

        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));

        assertEquals(LECTURE_WAS_COMPLETED, exc.getMessage());
    }


    @Test
    void should_complete_lecture_with_different_student() {


        final Course course = aCourse().build();

        final User student1 = anUser().build();
        when(userPersistence.get(any())).thenReturn(student1);


        final Lecture lecture = aLecture(course).build();
        when(lecturePersistence.get(any())).thenReturn(lecture);

        final User student2 = anUser().build();
        final Subscription subscription = aSubscription(student2, course).build();
        when(subscriptionPersistence.get(any())).thenReturn(subscription);

        CompleteLectureUseCaseInput input = CompleteLectureUseCaseInput.builder()
                .subscriptionId(subscription.getId())
                .lectureId(lecture.getId()).build();

        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));

        assertEquals(SUBSCRIPTION_DOES_NOT_BELONG_TO_STUDENT, exc.getMessage());
    }


}