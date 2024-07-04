package com.project.mybnb.security;

import com.project.mybnb.businessMember.exception.BusinessMemberException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.crypto.SecretKey;
import java.util.Date;

public class TokenProvider {

    public static final Long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 1; // ACCESS 토근 만료 시간: 1시간
    public static final Long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 6; // Refresh 토큰 만료 시간 : 6시간

    private static final SecretKey key = Jwts.SIG.HS512.key().build();

    private final UserDetailsService userDetailsService;

    @Autowired
    public TokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public static String createToken(Long id, String email, Long expiredTime) {
        Date now = new Date();
        Date expiredDate = new Date(System.currentTimeMillis() + expiredTime);

        return Jwts.builder().header().type("jwt").and()
                .claim("email", email)
                .claim("jti", id.toString())
                .issuedAt(now).expiration(expiredDate)
                .signWith(key).compact();
    }

    public static boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new BusinessMemberException("BSM011", "잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new BusinessMemberException("BSM012", "만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new BusinessMemberException("BSM013", "지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new BusinessMemberException("BSM014", "JWT 토큰이 잘못되었습니다.");
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getEmailFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    private String getEmailFromToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }

}
