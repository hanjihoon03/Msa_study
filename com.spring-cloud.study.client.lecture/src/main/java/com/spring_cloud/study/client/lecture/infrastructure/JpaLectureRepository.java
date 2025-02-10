package com.spring_cloud.study.client.lecture.infrastructure;

import com.spring_cloud.study.client.lecture.infrastructure.custom.JpaLectureRepositoryCustom;
import com.spring_cloud.study.client.lecture.model.Lecture;
import com.spring_cloud.study.client.lecture.model.repository.LectureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaLectureRepository extends LectureRepository, JpaRepository<Lecture, Long>, JpaLectureRepositoryCustom {
}
