package com.spring_cloud.study.client.enrollment.presentation;

import com.spring_cloud.study.client.enrollment.application.EnrollmentService;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public EnrollmentResponseDto saveEnrollment() {
        return null;
    }

    @GetMapping
    public Page<EnrollmentResponseDto> searchEnrollment() {
        return null;
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
