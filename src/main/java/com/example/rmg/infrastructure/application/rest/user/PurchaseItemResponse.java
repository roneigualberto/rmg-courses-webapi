package com.example.rmg.infrastructure.application.rest.user;


import com.example.rmg.infrastructure.application.rest.course.CourseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class PurchaseItemResponse {

    private UUID id;

    private CourseResponse course;

    private Double price;
}
