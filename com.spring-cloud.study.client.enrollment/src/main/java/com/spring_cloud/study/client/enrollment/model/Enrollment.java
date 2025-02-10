package com.spring_cloud.study.client.enrollment.model;

import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentRequestDto;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentResponseDto;
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

    public EnrollmentResponseDto toResponseDto() {
        return new EnrollmentResponseDto(
                this.id,
                this.enrollmentLectureIds,
                this.createAt,
                this.createBy,
                this.updateAt,
                this.updateBy);
    }

    public void updateEnrollment(EnrollmentRequestDto enrollmentRequestDto, Long userId) {
        this.enrollmentLectureIds = enrollmentRequestDto.getEnrollmentLectureIds();
        this.updateAt = LocalDateTime.now();
        this.updateBy = userId;
    }

    public void deleteEnrollment(String deleteBy) {
        this.deleteAt = LocalDateTime.now();
        this.deleteBy = deleteBy;
    }

    public void deleteDetailsEnrollment(Long lectureId) {
        this.enrollmentLectureIds.removeIf(id -> id.equals(lectureId));
    }
}
