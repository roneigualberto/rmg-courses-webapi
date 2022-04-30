package com.example.rmg.infrastructure.persistence.jpa.lecture;


import com.example.rmg.domain.course.valueobject.LectureType;
import com.example.rmg.infrastructure.persistence.jpa.course.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "lecture", uniqueConstraints = {
        @UniqueConstraint(name = "lecture_un", columnNames = {"course_id", "lecture_order"})
})
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CourseEntity course;

    @Enumerated(EnumType.STRING)
    private LectureType type;

    @Column(nullable = false, name = "lecture_order")
    private Integer order;

}
