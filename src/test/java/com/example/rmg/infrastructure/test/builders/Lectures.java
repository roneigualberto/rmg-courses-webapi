package com.example.rmg.infrastructure.test.builders;

import com.example.rmg.application.rest.course.LectureRequest;
import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.entity.Lecture;
import com.example.rmg.domain.course.valueobject.LectureType;
import com.example.rmg.usecase.course.lecture.common.input.LectureForm;

public class Lectures {


    public static final String LECTURE_TITLE = "Lecture Title";
    public static final int LECTURE_ORDER = 1;
    public static final LectureType LECTURE_TYPE = LectureType.VIDEO;

    public static LectureForm.LectureFormBuilder aLectureForm() {
        return LectureForm.builder()
                .title(LECTURE_TITLE)
                .order(LECTURE_ORDER)
                .type(LECTURE_TYPE);
    }

    public static LectureRequest.LectureRequestBuilder aLectureRequest() {
        return LectureRequest.builder()
                .title(LECTURE_TITLE)
                .order(LECTURE_ORDER)
                .type(LECTURE_TYPE);
    }

    public static Lecture.LectureBuilder aLecture(Course course) {
        return Lecture.builder()
                .course(course)
                .title(LECTURE_TITLE)
                .order(LECTURE_ORDER)
                .type(LECTURE_TYPE);
    }

}
