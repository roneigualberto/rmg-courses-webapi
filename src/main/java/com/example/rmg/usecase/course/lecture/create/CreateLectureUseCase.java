package com.example.rmg.usecase.course.lecture.create;

import com.example.rmg.domain.common.exception.DomainException;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.usecase.common.UseCase;
import com.example.rmg.usecase.course.lecture.common.input.LectureForm;
import lombok.RequiredArgsConstructor;

import static com.example.rmg.domain.course.messages.CourseMessages.LECTURE_ORDER_EXISTS;

@RequiredArgsConstructor
public class CreateLectureUseCase implements UseCase<CreateLectureUseCaseInput, CreateLectureUseCaseOutput> {


    private final CoursePersistence coursePersistence;

    private final LecturePersistence lecturePersistence;


    @Override
    public CreateLectureUseCaseOutput execute(CreateLectureUseCaseInput input) {

        Course course = coursePersistence.get(input.getCourseId());
        LectureForm form = input.getLecture();

        validate(course, form);

        Lecture lecture = Lecture.builder()
                .course(course)
                .order(form.getOrder())
                .title(form.getTitle())
                .type(form.getType())
                .build();

        lecture.valid();

        lecturePersistence.save(lecture);


        return CreateLectureUseCaseOutput.of(lecture);
    }

    private void validate(Course course, LectureForm form) {
        boolean existsWithOrder = lecturePersistence.existsWithOrder(course, form.getOrder());

        if (existsWithOrder) {
            throw new DomainException(LECTURE_ORDER_EXISTS);
        }
    }
}
