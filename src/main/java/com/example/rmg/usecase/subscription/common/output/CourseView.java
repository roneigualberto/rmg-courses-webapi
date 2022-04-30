package com.example.rmg.usecase.subscription.common.output;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class CourseView {

    private UUID id;

    private String name;

    private String title;
}
