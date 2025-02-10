package com.spring_cloud.study.client.enrollment.model;

import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PROTECTED)
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "enrollment_lecture", joinColumns = @JoinColumn(name = "enrollemnt_id"))
    @Column(name = "enrollment_lecture_id")
    private List<Long> enrollmentLectureIds;

    private LocalDateTime createAt;
    private Long createBy;
    private LocalDateTime updateAt;
    private Long updateBy;
    private LocalDateTime deleteAt;
    private String deleteBy;


    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }

    public static Enrollment saveEnrollment(EnrollmentRequestDto requestDto, Long userId) {
        return Enrollment.builder()
                .enrollmentLectureIds(requestDto.getEnrollmentLectureIds())
                .createBy(userId)
                .build();
    }
}
