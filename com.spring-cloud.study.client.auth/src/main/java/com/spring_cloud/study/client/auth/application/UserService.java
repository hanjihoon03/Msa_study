package com.spring_cloud.study.client.auth.application;

import com.spring_cloud.study.client.auth.model.User;
import com.spring_cloud.study.client.auth.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User signUp(User user) {
        userRepository.save(user);
    }


}
