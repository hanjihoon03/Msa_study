package com.spring_cloud.study.client.lecture.infrastructure.custom;

import com.spring_cloud.study.client.lecture.presentation.dto.LectureResponseDto;
import com.spring_cloud.study.client.lecture.presentation.dto.LectureSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JpaLectureRepositoryCustom {

    Page<LectureResponseDto> searchLecture(LectureSearchDto lectureSearchDto, Pageable pageable);
}
