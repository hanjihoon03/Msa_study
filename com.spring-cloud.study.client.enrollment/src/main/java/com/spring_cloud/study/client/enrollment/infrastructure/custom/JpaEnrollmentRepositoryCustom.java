package com.spring_cloud.study.client.enrollment.infrastructure.custom;

import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentRequestDto;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentResponseDto;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JpaEnrollmentRepositoryCustom {

    Page<EnrollmentResponseDto> searchEnrollment(EnrollmentSearchDto enrollmentSearchDto, Pageable pageable, Long userId);
}
