package com.example.rmg.usecase.subscription.completelecture;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.domain.subscription.entity.CompletedLecture;
import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.domain.subscription.persistence.CompletedLecturePersistence;
import com.example.rmg.domain.subscription.persistence.SubscriptionPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.example.rmg.domain.course.messages.CourseMessages.LECTURE_DOES_NOT_BELONGS_TO_COURSE;
import static com.example.rmg.domain.subscription.messages.SubscriptionMessages.SUBSCRIPTION_DOES_NOT_BELONG_TO_STUDENT;

@RequiredArgsConstructor
public class CompleteLectureUseCase implements UseCase<CompleteLectureUseCaseInput, CompleteLectureUseCaseOutput> {


    private final CompletedLecturePersistence completedLecturePersistence;

    private final SubscriptionPersistence subscriptionPersistence;

    private final LecturePersistence lecturePersistence;

    private final UserPersistence userPersistence;


    @Override
    public CompleteLectureUseCaseOutput execute(CompleteLectureUseCaseInput input) {


        final User student = userPersistence.get(input.getStudentId());

        final Subscription subscription = subscriptionPersistence.get(input.getSubscriptionId());

        final Lecture lecture = lecturePersistence.get(input.getLectureId());

        if (subscription.doNotBelong(student)) {
            throw new DomainException(SUBSCRIPTION_DOES_NOT_BELONG_TO_STUDENT);
        }

        if (lecture.doNotBelong(subscription.getCourse())) {
            throw new DomainException(LECTURE_DOES_NOT_BELONGS_TO_COURSE);
        }

        boolean isCompleted = completedLecturePersistence.isCompleted(subscription, lecture);

        if (isCompleted) {
            throw new DomainException("Lecture was already completed");
        }

        final CompletedLecture completedLecture = CompletedLecture.builder()
                .subscription(subscription)
                .lecture(lecture)
                .completedAt(LocalDateTime.now())
                .build();

        completedLecturePersistence.save(completedLecture);

        return CompleteLectureUseCaseOutput.builder().build();
    }
}
