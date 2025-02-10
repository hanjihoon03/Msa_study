package com.spring_cloud.study.client.lecture.presentation;

import com.spring_cloud.study.client.lecture.application.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    public String saveLecture() {
        return null;
    }

    @GetMapping
    public String findAllLecture() {
        return null;
    }

    @GetMapping("/{title}")
    public String findLectureTitle() {
        return null;
    }

    @PutMapping("/{id}")
    public String updateLecture() {
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteLecture() {
        return null;
    }


}
