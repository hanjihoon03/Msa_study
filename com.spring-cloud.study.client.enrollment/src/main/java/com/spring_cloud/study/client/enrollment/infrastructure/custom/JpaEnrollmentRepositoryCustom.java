package com.spring_cloud.study.client.enrollment.infrastructure.custom;

import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentRequestDto;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JpaEnrollmentRepositoryCustom {

    Page<EnrollmentResponseDto> searchEnrollment(EnrollmentRequestDto enrollmentRequestDto, Pageable pageable);
}
