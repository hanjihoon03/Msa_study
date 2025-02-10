package com.spring_cloud.study.client.enrollment.infrastructure.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaEnrollmentRepositoryImpl {

    private final JPAQueryFactory queryFactory;


}
