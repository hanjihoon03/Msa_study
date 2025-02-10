package com.spring_cloud.study.client.lecture.model;

import com.spring_cloud.study.client.lecture.presentation.dto.LectureRequestDto;
import com.spring_cloud.study.client.lecture.presentation.dto.LectureResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(access = AccessLevel.PROTECTED)
@Table(name = "lectures")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //강의 제목
    private String title;
    //강의 설명
    private String description;
    //강사 이름
    private String instructor;

    private int price;

    private LocalDateTime createAt;
    private Long createBy;
    private LocalDateTime updateAt;
    private Long updateBy;
    private LocalDateTime deleteAt;
    private String deleteBy;


    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }

    public static Lecture createLecture(LectureRequestDto lectureRequestDto, Long userId) {
        return Lecture.builder()
                .title(lectureRequestDto.getTitle())
                .description(lectureRequestDto.getDescription())
                .instructor(lectureRequestDto.getInstructor())
                .price(lectureRequestDto.getPrice())
                .createBy(userId)
                .build();
    }

    public LectureResponseDto toResponseDto() {
        return new LectureResponseDto(
                this.id,
                this.title,
                this.description,
                this.instructor,
                this.createAt,
                this.createBy,
                this.updateAt,
                this.updateBy,
                this.deleteAt,
                this.deleteBy
        );
    }

    public void updateLecture(LectureRequestDto lectureRequestDto, Long userId) {
        this.title = lectureRequestDto.getTitle();
        this.description = lectureRequestDto.getDescription();
        this.instructor = lectureRequestDto.getInstructor();
        this.price = lectureRequestDto.getPrice();
        this.updateAt = LocalDateTime.now();
        this.updateBy = userId;
    }

    public void deleteLecture(String deleteBy) {
        this.deleteAt = LocalDateTime.now();
        this.deleteBy = deleteBy;
    }

}
