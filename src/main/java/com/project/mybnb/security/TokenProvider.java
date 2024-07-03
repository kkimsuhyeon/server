package com.project.mybnb.security;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class TokenProvider {

    private static final SecretKey key = Jwts.SIG.HS512.key().build();

    public static final Long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 1; // ACCESS 토근 만료 시간: 1시간
    public static final Long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 6; // Refresh 토큰 만료 시간 : 6시간

    public static String createToken(Long id) {
        Date now = new Date();
        Date expiredDate = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME);

        return Jwts.builder().header().type("jwt").and()
                .issuedAt(now).expiration(expiredDate)
                .signWith(key).compact();
    }

}
