package com.iloveleiyuxin.websitmanager.utils;

import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "com.iloveleiyuxin.websitmanager.jwt")
public class JwtUtils {

    private long expire;
    private String secret;
    private String header;

    // 生成jwt
    public String generateToken(String username) {

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)// 7天过期
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // 解析jwt
    public Claims getClaimByToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        }catch (ExpiredJwtException e){
            log.warn("LeiYuXin-Core警告：Token已过期！");
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }


}