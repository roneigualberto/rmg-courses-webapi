package com.example.rmg.application.rest.user;


import com.example.rmg.application.rest.course.CourseRequest;
import com.example.rmg.application.rest.course.CourseResponse;
import com.example.rmg.usecase.purchase.common.output.CourseView;
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
