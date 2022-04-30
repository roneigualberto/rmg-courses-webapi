package com.example.rmg.infrastructure.persistence.jpa.subscription;


import com.example.rmg.infrastructure.persistence.jpa.lecture.LectureEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "completed_lecture")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CompletedLectureEntity {


    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private SubscriptionEntity subscription;

    @ManyToOne
    @JoinColumn(nullable = false)
    private LectureEntity lecture;

    @Column(nullable = false)
    private LocalDateTime completedAt;


}
