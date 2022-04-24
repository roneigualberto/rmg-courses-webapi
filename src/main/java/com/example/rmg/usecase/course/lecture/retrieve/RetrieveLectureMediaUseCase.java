package com.example.rmg.usecase.course.lecture.retrieve;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.domain.course.storage.StorageService;
import com.example.rmg.usecase.common.UseCase;
import com.example.rmg.usecase.course.lecture.store.StoreLectureMediaUseCaseInput;
import com.example.rmg.usecase.course.lecture.store.StoreLectureUseCaseOutput;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;

import static com.example.rmg.domain.course.messages.CourseMessages.LECTURE_DOES_NOT_BELONGS_TO_COURSE;

@RequiredArgsConstructor
public class RetrieveLectureMediaUseCase implements UseCase<RetrieveLectureMediaUseCaseInput, RetrieveLectureMediaUseCaseOutput> {


    private final CoursePersistence coursePersistence;

    private final LecturePersistence lecturePersistence;

    private final StorageService storageService;


    @Override
    public RetrieveLectureMediaUseCaseOutput execute(RetrieveLectureMediaUseCaseInput input) {

        Course course = coursePersistence.get(input.getCourseId());
        Lecture lecture = lecturePersistence.get(input.getLectureId());

        if (!lecture.belongsTo(course)) {
            throw new DomainException(LECTURE_DOES_NOT_BELONGS_TO_COURSE);
        }

        InputStream media = storageService.get("lectures", lecture.getPath());

        return RetrieveLectureMediaUseCaseOutput.builder()
                .media(media)
                .build();
    }
}
