package com.example.rmg.domain.course.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LectureType {

    VIDEO("mp4", "video/mp4"),
    PDF("pdf", "application/pdf"),
    HTML("html", "text/html");

    private final String extension;

    private final String mimeType;
}
