package com.spring_cloud.study.client.enrollment.application;

import com.spring_cloud.study.client.enrollment.model.Enrollment;
import com.spring_cloud.study.client.enrollment.model.repository.EnrollmentRepository;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentRequestDto;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentResponseDto;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    public Page<EnrollmentResponseDto> searchEnrollment(EnrollmentSearchDto enrollmentSearchDto, Pageable pageable, Long userId) {
        return enrollmentRepository.searchEnrollment(enrollmentSearchDto, pageable, userId);
    }

    @Transactional(readOnly = true)
    public EnrollmentResponseDto findByEnrollmentId(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .filter(e -> e.getDeleteAt() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없는 주문 번호입니다."));

        return new EnrollmentResponseDto(enrollment);

    }

    @Transactional
    public EnrollmentResponseDto updateEnrollment(Long enrollmentId, EnrollmentRequestDto enrollmentRequestDto, Long userId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .filter(e -> e.getDeleteAt() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수정할 수 없는 주문 번호입니다."));
        enrollment.updateEnrollment(enrollmentRequestDto, userId);

        return new EnrollmentResponseDto(enrollment);
    }


    @Transactional
    public void deleteEnrollment(Long enrollmentId, String deleteBy) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .filter(e -> e.getDeleteAt() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "이미 삭제 된 주문 번호입니다."));
        enrollment.deleteEnrollment(deleteBy);
    }

    @Transactional
    public void deleteDetailsEnrollment(Long enrollmentId, Long lectureId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .filter(e -> e.getDeleteAt() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "이미 삭제 된 주문 번호입니다."));
        enrollment.deleteDetailsEnrollment(lectureId);

    }
}
