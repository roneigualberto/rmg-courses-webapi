package com.example.rmg.usecase.subscription.undocompletelecture;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class UndoCompleteLectureUseCaseInput {


    private UUID studentId;

    private UUID subscriptionId;

    private UUID lectureId;


}
