package com.spring_cloud.study.client.lecture.presentation.dto;

import com.spring_cloud.study.client.lecture.model.Lecture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LectureResponseDto {
    private Long id;
    private String title;
    private String description;
    private String instructor;

    private LocalDateTime createAt;
    private Long createBy;
    private LocalDateTime updateAt;
    private Long updateBy;
    private LocalDateTime deleteAt;
    private String deleteBy;

    public LectureResponseDto(Lecture lecture) {
        this.id = lecture.getId();
        this.title = lecture.getTitle();
        this.description = lecture.getDescription();
        this.instructor = lecture.getInstructor();
        this.createAt = lecture.getCreateAt();
        this.createBy = lecture.getCreateBy();
        this.updateAt = lecture.getUpdateAt();
        this.updateBy = lecture.getUpdateBy();
        this.deleteAt = lecture.getDeleteAt();
        this.deleteBy = lecture.getDeleteBy();
    }
}
