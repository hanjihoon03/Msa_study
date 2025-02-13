package com.spring_cloud.study.client.auth.model;

public enum UserRoleEnum {

    MEMBER(Authority.MEMBER),  // 사용자 권한
    ADMIN(Authority.ADMIN);  // 관리자 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String MEMBER = "MEMBER";
        public static final String ADMIN = "ADMIN";
    }

}
