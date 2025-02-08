package com.spring_cloud.study.client.auth.infrastructure;

import com.spring_cloud.study.client.auth.model.User;
import com.spring_cloud.study.client.auth.model.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends UserRepository, JpaRepository<User, Long> {
}
