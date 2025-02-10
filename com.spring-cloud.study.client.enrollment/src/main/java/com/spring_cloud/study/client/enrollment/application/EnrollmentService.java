package com.spring_cloud.study.client.enrollment.application;

import com.spring_cloud.study.client.enrollment.model.Enrollment;
import com.spring_cloud.study.client.enrollment.model.repository.EnrollmentRepository;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentRequestDto;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentResponseDto;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public EnrollmentResponseDto saveEnrollment(EnrollmentRequestDto enrollmentRequestDto, Long userId) {

        Enrollment enrollment = Enrollment.saveEnrollment(enrollmentRequestDto, userId);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return new EnrollmentResponseDto(savedEnrollment);
    }

    @Transactional(readOnly = true)
    public Page<EnrollmentResponseDto> searchEnrollment(EnrollmentSearchDto enrollmentSearchDto, Pageable pageable) {


    }
}
