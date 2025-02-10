package com.spring_cloud.study.client.lecture.model.repository;

import com.spring_cloud.study.client.lecture.model.Lecture;
import com.spring_cloud.study.client.lecture.presentation.dto.LectureResponseDto;
import com.spring_cloud.study.client.lecture.presentation.dto.LectureSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface LectureRepository {

    Lecture save(Lecture lecture);
    Lecture findByTitle(String title);
    Optional<Lecture> findById(Long lectureId);

    Page<LectureResponseDto> searchLecture(LectureSearchDto lectureSearchDto, Pageable pageable);
}
