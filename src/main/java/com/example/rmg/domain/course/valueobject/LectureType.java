package com.example.rmg.domain.course.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LectureType {

    VIDEO("mp4"),
    PDF("pdf"),
    HTML("html");

    private final String extension;
}
