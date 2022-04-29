package com.example.rmg.domain.purchase.entity;


import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import com.example.rmg.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Builder
public class Purchase {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotNull
    private User buyer;

    @NotNull
    @Builder.Default
    private Double total = 0.0;

    @NotNull
    private PaymentMethod paymentMethod;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @ToString.Exclude
    private List<PurchaseItem> items = new ArrayList<>();

    public void addCourse(Course course) {
        final PurchaseItem item = addItem(course);
        this.items.add(item);
        this.total += item.getPrice();
    }

    public PurchaseItem addItem(Course course) {
        return PurchaseItem.builder()
                .purchase(this)
                .course(course)
                .price(course.getPrice())
                .build();
    }
}
