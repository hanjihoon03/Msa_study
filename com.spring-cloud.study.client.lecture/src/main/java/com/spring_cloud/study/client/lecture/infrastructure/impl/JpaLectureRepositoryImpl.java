package com.spring_cloud.study.client.lecture.infrastructure.impl;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring_cloud.study.client.lecture.infrastructure.custom.JpaLectureRepositoryCustom;
import com.spring_cloud.study.client.lecture.model.Lecture;
import com.spring_cloud.study.client.lecture.presentation.dto.LectureResponseDto;
import com.spring_cloud.study.client.lecture.presentation.dto.LectureSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.spring_cloud.study.client.lecture.model.QLecture.lecture;

@RequiredArgsConstructor
public class JpaLectureRepositoryImpl implements JpaLectureRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    @Override
    public Page<LectureResponseDto> searchLecture(LectureSearchDto lectureSearchDto, Pageable pageable) {

        List<OrderSpecifier<?>> orderSpecifierList = getAllOrderSpecifiers(pageable);

        JPAQuery<Lecture> jpaQuery = query(lecture, lectureSearchDto)
                .orderBy(orderSpecifierList.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Lecture> fetch = jpaQuery.fetch();

        List<LectureResponseDto> content = fetch.stream()
                .map(Lecture::toResponseDto)
                .collect(Collectors.toList());
        Long totalSize = query(Wildcard.count, lectureSearchDto)
                .fetchOne();
        if (totalSize == null) {
            totalSize = 0L;
        }

        return new PageImpl<>(content, pageable, totalSize);

    }

    private <T> JPAQuery<T> query(Expression<T> expr,LectureSearchDto lectureSearchDto) {
        return queryFactory
                .select(expr)
                .from(lecture)
                .where(
                        titleContains(lectureSearchDto.getTitle()),
                        descriptionContains(lectureSearchDto.getDescription()),
                        instructorContains(lectureSearchDto.getInstructor()),
                        priceRange(lectureSearchDto.getMinPrice(), lectureSearchDto.getMaxPrice())
                );
    }

    private BooleanExpression priceRange(Integer minPrice, Integer maxPrice) {
        if (minPrice != null && maxPrice != null) {
            return lecture.price.between(minPrice, maxPrice);
        } else if (minPrice != null) {
            return lecture.price.goe(minPrice);
        } else if (maxPrice != null) {
            return lecture.price.loe(maxPrice);
        } else {
            return null;
        }
    }

    private BooleanExpression instructorContains(String instructor) {
        return instructor != null ? lecture.instructor.containsIgnoreCase(instructor) : null;
    }

    private BooleanExpression descriptionContains(String description) {
        return description != null ? lecture.description.containsIgnoreCase(description) : null;
    }

    private BooleanExpression titleContains(String title) {
        return title != null ? lecture.title.containsIgnoreCase(title) : null;
    }

    private List<OrderSpecifier<?>> getAllOrderSpecifiers(Pageable pageable) {

        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        if (pageable.getSort() != null) {
            for (Sort.Order sortOrder : pageable.getSort()) {
                Order direction = sortOrder.isAscending() ? Order.ASC : Order.DESC;
                switch (sortOrder.getProperty()) {
                    case "createAt":
                        orderSpecifiers.add(new OrderSpecifier<>(direction, lecture.createAt));
                        break;
                    case "price":
                        orderSpecifiers.add(new OrderSpecifier<>(direction, lecture.price));
                        break;
                    default:
                        break;
                }

            }
        }else {
            orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, lecture.createAt));
        }

        return orderSpecifiers;
    }
}
