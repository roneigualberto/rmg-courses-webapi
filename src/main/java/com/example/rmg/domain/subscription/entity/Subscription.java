package com.example.rmg.domain.subscription.entity;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

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
}
