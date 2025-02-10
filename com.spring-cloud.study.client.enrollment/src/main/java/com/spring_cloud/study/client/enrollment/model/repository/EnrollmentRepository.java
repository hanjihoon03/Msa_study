package com.spring_cloud.study.client.enrollment.model.repository;

import com.spring_cloud.study.client.enrollment.model.Enrollment;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentRequestDto;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EnrollmentRepository {

    Enrollment save(Enrollment enrollment);

    Optional<Enrollment> findById(Long enrollmentId);

    Page<EnrollmentResponseDto> searchEnrollment(EnrollmentRequestDto enrollmentRequestDto, Pageable pageable);

}
