package com.spring_cloud.study.client.lecture.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureSearchDto {

    private String title;
    private String description;
    private String instructor;
    private Integer minPrice;
    private Integer maxPrice;
}
