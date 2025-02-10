package com.spring_cloud.study.client.enrollment.infrastructure;

import com.spring_cloud.study.client.enrollment.infrastructure.custom.JpaEnrollmentRepositoryCustom;
import com.spring_cloud.study.client.enrollment.model.Enrollment;
import com.spring_cloud.study.client.enrollment.model.repository.EnrollmentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEnrollmentRepository extends EnrollmentRepository, JpaRepository<Enrollment, Long>, JpaEnrollmentRepositoryCustom {
}
