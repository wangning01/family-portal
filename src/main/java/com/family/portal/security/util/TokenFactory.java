package com.family.portal.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.family.portal.security.constant.SecurityConstant.*;

public class TokenFactory {


    public static String createAccessToken(Authentication auth) {

        Claims claims = Jwts.claims().setSubject(auth.getName());
        claims.put("scopes", auth.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        return  token;
    }

    public static String createRefreshToken(Authentication auth) {


        Date currentDate = new Date();


        Claims claims = Jwts.claims().setSubject(auth.getName());
        List<String> scopes = new ArrayList<>();
        scopes.add("ROLE_REFRESH_TOKEN");
        claims.put("scopes", scopes);

        String token = Jwts.builder()
                .setClaims(claims)
//                .setIssuer(settings.getTokenIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(currentDate)
                .setExpiration(new Date( currentDate.getTime() + REFRESH_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        return token;
    }
}
