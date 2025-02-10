package com.spring_cloud.study.client.enrollment.presentation;

import com.spring_cloud.study.client.enrollment.application.EnrollmentService;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentRequestDto;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentResponseDto;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public EnrollmentResponseDto saveEnrollment(@RequestBody EnrollmentRequestDto enrollmentRequestDto,
                                                @RequestHeader(value = "X-User_Id") Long userId,
                                                @RequestHeader(value = "X-role") String role) {
        return enrollmentService.saveEnrollment(enrollmentRequestDto, userId);
    }

    @GetMapping
    public Page<EnrollmentResponseDto> searchEnrollment(EnrollmentSearchDto enrollmentSearchDto,
                                                        Pageable pageable,
                                                        @RequestHeader(value = "X-User_Id") Long userId,
                                                        @RequestHeader(value = "X-role") String role) {
        return enrollmentService.searchEnrollment(enrollmentSearchDto, pageable);
    }

    @GetMapping("/{enrollmentId}")
    public EnrollmentResponseDto findByEnrollmentId() {
        return null;
    }

    @PutMapping("/{enrollmentId}")
    public EnrollmentResponseDto updateEnrollment() {
        return null;
    }

    @DeleteMapping("/{enrollmentId}")
    public void deleteEnrollment() {

    }

    @DeleteMapping("/{enrollmentId}/{lectureId}")
    public void deleteDetailsEnrollment() {

    }
}
