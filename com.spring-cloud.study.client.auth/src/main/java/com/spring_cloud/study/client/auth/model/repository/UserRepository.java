package com.spring_cloud.study.client.auth.model.repository;

import com.spring_cloud.study.client.auth.model.User;

public interface UserRepository {
    User save(User user);
}
