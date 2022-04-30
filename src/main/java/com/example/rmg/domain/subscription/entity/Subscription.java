package com.example.rmg.domain.subscription.entity;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Getter
@Builder
public class Subscription {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotNull
    private User student;

    @NotNull
    private Course course;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private Review review;

    private boolean finished;

    private LocalDateTime finishDate;

    public boolean doNotBelong(User student) {
        return !this.student.equals(student);
    }

    public void finish(Integer rating, String comment) {
        this.review = new Review(rating, comment);
        this.finishDate = LocalDateTime.now();
        this.finished = true;
    }
}
