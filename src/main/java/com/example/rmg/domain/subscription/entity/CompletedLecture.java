package com.example.rmg.domain.subscription.entity;

import com.example.rmg.domain.course.entity.Lecture;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Builder
public class CompletedLecture {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotNull
    private Subscription subscription;

    @NotNull
    private Lecture lecture;

    @NotNull
    private LocalDateTime completedAt;

}
