package com.huayue.springsecuritydemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/22.
 */
@Slf4j
public class JwtUtil {
    private static Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String subject, int expirationSeconds) {
        return Jwts.builder()
                .setClaims(null)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(KEY)
                .compact();
    }

    public static String parseToken(String token) throws JwtException {
        String subject;
        Claims claims = Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)  .getBody();
        subject = claims.getSubject();
        return subject;
    }

    /**
     * token过期时间为14天，如果超过14天用户没有进行任何请求，则token过期，需要重新登陆
     * 用户发送请求后，通过过滤器判断token有效期是否小于7天，如果是则发送新的token进行刷新
     * @param token
     * @return
     * @throws JwtException
     */
    public static String refreshToken(String token) throws JwtException {
        Date date = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody().getExpiration();
        if (DateUtil.subDate(new Date(), date) < 7) {
            return generateToken(Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody().getSubject(),300);
        }
        return null;
    }
}
