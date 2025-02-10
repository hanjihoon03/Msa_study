package com.spring_cloud.study.client.lecture.application;

import com.spring_cloud.study.client.lecture.model.Lecture;
import com.spring_cloud.study.client.lecture.model.repository.LectureRepository;
import com.spring_cloud.study.client.lecture.presentation.dto.LectureRequestDto;
import com.spring_cloud.study.client.lecture.presentation.dto.LectureResponseDto;
import com.spring_cloud.study.client.lecture.presentation.dto.LectureSearchDto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional
    public LectureResponseDto saveLecture(LectureRequestDto lectureRequestDto, Long userId) {
        Lecture createdlecture = Lecture.createLecture(lectureRequestDto, userId);
        Lecture savedLecture = lectureRepository.save(createdlecture);

        return new LectureResponseDto(savedLecture);
    }

    @Transactional(readOnly = true)
    public Page<LectureResponseDto> searchGetLecture(LectureSearchDto lectureSearchDto, Pageable pageable) {
        return lectureRepository.searchLecture(lectureSearchDto, pageable);
    }

    @Transactional(readOnly = true)
    public LectureResponseDto findLectureId(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .filter(l -> l.getDeleteAt() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제되거나 없는 강의입니다."));

        return new LectureResponseDto(lecture);
    }


    @Transactional
    public LectureResponseDto updateLecture(Long lectureId, LectureRequestDto lectureRequestDto, Long userId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .filter(l -> l.getDeleteAt() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수정할 수 없는 상태입니다."));
        lecture.updateLecture(lectureRequestDto, userId);

        return new LectureResponseDto(lecture);
    }

    @Transactional
    public void deleteLecture(Long lectureId, String deleteBy) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .filter(l -> l.getDeleteAt() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "이미 삭제된 상태입니다."));
        lecture.deleteLecture(deleteBy);
    }
}
