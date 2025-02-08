package com.spring_cloud.study.client.auth.model.repository;

import com.spring_cloud.study.client.auth.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long userId);
    Optional<User> findByUsername(String username);
}
