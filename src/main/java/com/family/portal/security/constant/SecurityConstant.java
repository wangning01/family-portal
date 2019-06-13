package com.family.portal.security.constant;

public class SecurityConstant {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long ACCESS_EXPIRATION_TIME = 60000; // 1 mins
    public static final long REFRESH_EXPIRATION_TIME = 86_400_000; // 5 mins

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
