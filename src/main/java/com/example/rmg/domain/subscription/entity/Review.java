package com.example.rmg.domain.subscription.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Getter
@Builder
public class Review {

    @Min(0)
    @Max(10)
    private Integer rating;

    private String comment;
}
