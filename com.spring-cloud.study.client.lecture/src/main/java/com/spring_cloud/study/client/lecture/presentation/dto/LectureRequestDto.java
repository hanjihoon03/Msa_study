package com.spring_cloud.study.client.lecture.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LectureRequestDto {

    private String title;

    private String description;

    private String instructor;

    private int price;
}
