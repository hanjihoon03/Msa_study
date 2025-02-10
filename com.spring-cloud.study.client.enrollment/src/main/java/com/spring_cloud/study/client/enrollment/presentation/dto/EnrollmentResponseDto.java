package com.spring_cloud.study.client.enrollment.presentation.dto;

import com.spring_cloud.study.client.enrollment.model.Enrollment;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponseDto {

    private Long id;
    private List<Long> enrollmentLectureIds;
    private LocalDateTime createAt;
    private Long createBy;
    private LocalDateTime updateAt;
    private Long updateBy;


    public EnrollmentResponseDto(Enrollment enrollment) {
        this.id = enrollment.getId();
        this.enrollmentLectureIds = enrollment.getEnrollmentLectureIds();
        this.createAt = enrollment.getCreateAt();
        this.createBy = enrollment.getCreateBy();
        this.updateAt = enrollment.getUpdateAt();
        this.updateBy = enrollment.getUpdateBy();
    }
}
