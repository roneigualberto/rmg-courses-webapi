package com.example.rmg.usecase.subscription.undocompletelecture;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.domain.subscription.entity.CompletedLecture;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.subscription.persistence.CompletedLecturePersistence;
import com.example.rmg.domain.subscription.persistence.SubscriptionPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.subscription.completelecture.CompleteLectureUseCaseInput;
import com.example.rmg.usecase.subscription.completelecture.CompleteLectureUseCaseOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
class UndoCompleteLectureUseCaseTest {


    @InjectMocks
    private UndoCompleteLectureUseCase useCase;

    @Mock
    private SubscriptionPersistence subscriptionPersistence;

    @Mock
    private CompletedLecturePersistence completedLecturePersistence;

    @Mock
    private LecturePersistence lecturePersistence;

    @Mock
    private UserPersistence userPersistence;


    @Test
    void should_undo_complete_uncompleted_lecture() {

        final Course course = aCourse().build();

        final User student = anUser().build();
        when(userPersistence.get(any())).thenReturn(student);

        final Lecture lecture = aLecture(course).build();
        when(lecturePersistence.get(any())).thenReturn(lecture);

        final Subscription subscription = aSubscription(student, course).build();
        when(subscriptionPersistence.get(any())).thenReturn(subscription);

        UndoCompleteLectureUseCaseInput input = UndoCompleteLectureUseCaseInput.builder()
                .subscriptionId(subscription.getId())
                .lectureId(lecture.getId()).build();


        DomainException exc = assertThrows(DomainException.class, () -> useCase.execute(input));

        assertEquals("Lecture is not completed", exc.getMessage());


    }

    @Test
    void should_undo_complete_lecture() {

        final Course course = aCourse().build();

        final User student = anUser().build();
        when(userPersistence.get(any())).thenReturn(student);

        final Lecture lecture = aLecture(course).build();
        when(lecturePersistence.get(any())).thenReturn(lecture);

        final Subscription subscription = aSubscription(student, course).build();
        when(subscriptionPersistence.get(any())).thenReturn(subscription);

        final CompletedLecture completedLecture = CompletedLecture.builder().lecture(lecture)
                .subscription(subscription)
                .build();
        when(completedLecturePersistence.findBySubscriptionAndLecture(any(), any())).thenReturn(Optional.of(completedLecture));

        UndoCompleteLectureUseCaseInput input = UndoCompleteLectureUseCaseInput.builder()
                .subscriptionId(subscription.getId())
                .lectureId(lecture.getId()).build();

        UndoCompleteLectureUseCaseOutput output = useCase.execute(input);

        verify(completedLecturePersistence).deleteById(any());
    }


}