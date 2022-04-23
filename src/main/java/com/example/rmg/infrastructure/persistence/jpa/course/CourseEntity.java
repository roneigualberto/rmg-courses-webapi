package com.example.rmg.infrastructure.persistence.jpa.course;


import com.example.rmg.domain.category.valueobject.CategoryGroup;
import com.example.rmg.domain.course.valueobject.SkillLevel;
import com.example.rmg.infrastructure.persistence.jpa.category.CategoryEntity;
import com.example.rmg.infrastructure.persistence.jpa.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "course")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private UserEntity instructor;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    private CategoryEntity category;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;

    @Column(nullable = false, length = 12, scale = 2)
    private Double price;

    @Column
    @Builder.Default
    private Boolean published = Boolean.FALSE;

    @Column
    private LocalDateTime publishDate;



}
