package com.example.rmg.usecase.course.lecture.store;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.domain.course.storage.StorageService;
import com.example.rmg.usecase.common.UseCase;
import lombok.RequiredArgsConstructor;

import static com.example.rmg.domain.course.messages.CourseMessages.LECTURE_DOES_NOT_BELONGS_TO_COURSE;

@RequiredArgsConstructor
public class StoreLectureMediaUseCase implements UseCase<StoreLectureMediaUseCaseInput, StoreLectureUseCaseOutput> {


    private final CoursePersistence coursePersistence;

    private final LecturePersistence lecturePersistence;

    private final StorageService storageService;

    @Override
    public StoreLectureUseCaseOutput execute(StoreLectureMediaUseCaseInput input) {

        Course course = coursePersistence.get(input.getCourseId());
        Lecture lecture = lecturePersistence.get(input.getLectureId());

        if (!lecture.belongsTo(course)) {
            throw new DomainException(LECTURE_DOES_NOT_BELONGS_TO_COURSE);
        }

        storageService.store("lectures", lecture.getPath(), input.getMedia());

        return StoreLectureUseCaseOutput.builder().build();
    }
}
