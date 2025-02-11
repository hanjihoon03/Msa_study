package com.spring_cloud.study.client.lecture.presentation;

import com.spring_cloud.study.client.lecture.application.LectureService;
import com.spring_cloud.study.client.lecture.presentation.dto.LectureRequestDto;
import com.spring_cloud.study.client.lecture.presentation.dto.LectureResponseDto;
import com.spring_cloud.study.client.lecture.presentation.dto.LectureSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
@Slf4j
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    public LectureResponseDto saveLecture(@RequestBody LectureRequestDto lectureRequestDto,
                                          @RequestHeader(value = "X-User-Id", required = true) Long userId,
                                          @RequestHeader(value = "X-Role", required = true) String role) {

        log.info("Received UserId: {}, Role: {}", userId, role);
        if (!role.equals("ADMIN")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근 불가한 권한입니다.");
        }

        return lectureService.saveLecture(lectureRequestDto, userId);
    }

    @GetMapping
    public Page<LectureResponseDto> findAllLecture(LectureSearchDto lectureSearchDto,
                                                   Pageable pageable) {
        return lectureService.searchGetLecture(lectureSearchDto, pageable);
    }

    @GetMapping("/{lectureId}")
    public LectureResponseDto findLectureId(@PathVariable Long lectureId) {
        return lectureService.findLectureId(lectureId);
    }

    @PutMapping("/{lectureId}")
    public LectureResponseDto updateLecture(@PathVariable("lectureId") Long lectureId,
                                            @RequestBody LectureRequestDto lectureRequestDto,
                                            @RequestHeader(value = "X-User-Id", required = true) Long userId,
                                            @RequestHeader(value = "X-Role", required = true) String role) {
        return lectureService.updateLecture(lectureId, lectureRequestDto, userId);
    }

    @DeleteMapping("/{lectureId}")
    public void deleteLecture(@PathVariable("lectureId") Long lectureId, @RequestParam String deleteBy) {
        lectureService.deleteLecture(lectureId, deleteBy);
    }


}
