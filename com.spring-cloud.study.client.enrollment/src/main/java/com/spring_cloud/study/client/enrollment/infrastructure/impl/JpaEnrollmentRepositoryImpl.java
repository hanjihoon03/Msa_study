package com.spring_cloud.study.client.enrollment.infrastructure.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring_cloud.study.client.enrollment.infrastructure.custom.JpaEnrollmentRepositoryCustom;
import com.spring_cloud.study.client.enrollment.model.Enrollment;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentResponseDto;
import com.spring_cloud.study.client.enrollment.presentation.dto.EnrollmentSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.spring_cloud.study.client.enrollment.model.QEnrollment.enrollment;

@RequiredArgsConstructor
public class JpaEnrollmentRepositoryImpl implements JpaEnrollmentRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<EnrollmentResponseDto> searchEnrollment(EnrollmentSearchDto enrollmentSearchDto, Pageable pageable, Long userId) {
        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers(pageable);

        QueryResults<Enrollment> enrollmentQueryResults = queryFactory.selectFrom(enrollment)
                .where(
                        enrollmentLectureIdsIn(enrollmentSearchDto.getEnrollmentLectureIds()),
                        enrollment.createBy.eq(userId)
                )
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<EnrollmentResponseDto> collect = enrollmentQueryResults.getResults().stream()
                .map(Enrollment::toResponseDto)
                .collect(Collectors.toList());

        long total = enrollmentQueryResults.getTotal();

        return new PageImpl<>(collect, pageable, total);
    }

    private BooleanExpression enrollmentLectureIdsIn(List<Long> enrollmentLectureIds) {
        return enrollmentLectureIds != null && !enrollmentLectureIds.isEmpty() ?
                enrollment.enrollmentLectureIds.any().in(enrollmentLectureIds) : null;
    }

    private List<OrderSpecifier<?>> getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        if (pageable.getSort() != null) {
            for (Sort.Order sortOrder : pageable.getSort()) {
                Order direction = sortOrder.isAscending() ? Order.ASC : Order.DESC;
                switch (sortOrder.getProperty()) {
                    case "createAt":
                        orders.add(new OrderSpecifier<>(direction, enrollment.createAt));
                        break;
                    default:
                        break;
                }
            }
        }
        return orders;
    }
}
