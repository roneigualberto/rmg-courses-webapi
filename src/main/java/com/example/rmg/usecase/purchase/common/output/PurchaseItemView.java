package com.example.rmg.usecase.purchase.common.output;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class PurchaseItemView {

    private UUID id;

    private CourseView course;

    private Double price;
}
