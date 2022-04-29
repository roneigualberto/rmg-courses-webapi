package com.example.rmg.domain.purchase.entity;

import com.example.rmg.domain.course.entity.Course;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
@Getter
@Builder
public class PurchaseItem {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotNull
    private Purchase purchase;

    @NotNull
    private Course course;

    @NotNull
    private Double price;



}
