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
                                                @RequestHeader(value = "X-User-Id") Long userId,
                                                @RequestHeader(value = "X-role") String role) {
        return enrollmentService.saveEnrollment(enrollmentRequestDto, userId);
    }

    @GetMapping
    public Page<EnrollmentResponseDto> searchEnrollment(EnrollmentSearchDto enrollmentSearchDto,
                                                        Pageable pageable,
                                                        @RequestHeader(value = "X-User-Id") Long userId,
                                                        @RequestHeader(value = "X-role") String role) {
        return enrollmentService.searchEnrollment(enrollmentSearchDto, pageable, userId);
    }

    @GetMapping("/{enrollmentId}")
    public EnrollmentResponseDto findByEnrollmentId(@PathVariable Long enrollmentId) {
        return enrollmentService.findByEnrollmentId(enrollmentId);
    }

    @PutMapping("/{enrollmentId}")
    public EnrollmentResponseDto updateEnrollment(@PathVariable("enrollmentId") Long enrollmentId,
                                                  @RequestBody EnrollmentRequestDto enrollmentRequestDto,
                                                  @RequestHeader(value = "X-User-Id") Long userId,
                                                  @RequestHeader(value = "X-role") String role) {
        return enrollmentService.updateEnrollment(enrollmentId, enrollmentRequestDto, userId);
    }

    @DeleteMapping("/{enrollmentId}")
    public void deleteEnrollment(@PathVariable("enrollmentId") Long enrollmentId, @RequestParam String deleteBy) {
        enrollmentService.deleteEnrollment(enrollmentId, deleteBy);
    }

    @DeleteMapping("/{enrollmentId}/{lectureId}")
    public void deleteDetailsEnrollment(@PathVariable("enrollmentId") Long enrollmentId,
                                        @PathVariable("lectureId") Long lectureId) {
        enrollmentService.deleteDetailsEnrollment(enrollmentId, lectureId);
    }
}
