package com.example.rmg.infrastructure.persistence.jpa.purchase;


import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "purchase_item")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItemEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PurchaseEntity purchase;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CourseEntity course;

    @Column(nullable = false)
    private Double price;

}
