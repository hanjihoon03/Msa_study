package com.spring_cloud.study.client.auth.application;

import com.spring_cloud.study.client.auth.model.User;
import com.spring_cloud.study.client.auth.model.UserRoleEnum;
import com.spring_cloud.study.client.auth.presentation.dto.SignInRequest;
import com.spring_cloud.study.client.auth.model.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class UserService {

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    private final SecretKey secretKey;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(@Value("${service.jwt.secret-key}") String secretKey,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signUp(User user) {
        user.encodeUserPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String signInCreateAccessToken(SignInRequest sign) {
        User signInUser = userRepository.findByUsername(sign.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되어 있지 않은 이름입니다."));

        if (!passwordEncoder.matches(sign.getPassword(), signInUser.getPassword())) {
            throw new IllegalArgumentException("틀린 패스워드 입니다.");
        }

        return createAccessToken(signInUser.getUserId(), signInUser.getUserRoleEnum());

    }

    public String createAccessToken(Long userId, UserRoleEnum role) {
        return Jwts.builder()
                .claim("user_id", userId)
                .claim("role", role.getAuthority())
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();

    }


}
