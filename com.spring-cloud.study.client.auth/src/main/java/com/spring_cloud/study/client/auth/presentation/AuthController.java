package com.spring_cloud.study.client.auth.presentation;

import com.spring_cloud.study.client.auth.application.UserService;
import com.spring_cloud.study.client.auth.model.User;
import com.spring_cloud.study.client.auth.model.dto.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PostMapping("/signIn")
    public ResponseEntity<?> signInCreateAuthenticationToken(@RequestBody SignInRequest signInRequest) {
        String token = userService.signInCreateAccessToken(signInRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("signUp")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        User signUpUser = userService.signUp(user);
        return ResponseEntity.ok(signUpUser);
    }
}
