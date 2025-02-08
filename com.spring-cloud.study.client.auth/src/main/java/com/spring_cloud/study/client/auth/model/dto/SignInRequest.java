package com.spring_cloud.study.client.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    //username이 유니크 값이라는 전제
    private String username;
    private String password;
}
