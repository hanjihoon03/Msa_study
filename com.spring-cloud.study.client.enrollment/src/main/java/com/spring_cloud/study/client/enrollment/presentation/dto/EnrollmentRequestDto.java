package com.spring_cloud.study.client.enrollment.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentRequestDto {

    private List<Long> enrollmentLectureIds;

}
