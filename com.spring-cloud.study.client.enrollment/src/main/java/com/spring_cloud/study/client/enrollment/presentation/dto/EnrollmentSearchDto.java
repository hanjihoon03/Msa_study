package com.spring_cloud.study.client.enrollment.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentSearchDto {
    private List<Long> enrollmentLectureIds;
    private String sortBy;
    private Pageable pageable;
}
