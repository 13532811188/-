package com.example.tpshop_test.entity;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    // 有效期24小时
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;
    // 密钥（实际项目中建议放在配置文件，且长度足够长）
    private static final String SECRET_KEY = "aGVsbG8gd29ybGQgdGhpcyBpcyBhIHNlY3JldCBrZXkgZm9yIGp3dCBrZXk=";

    // 生成token（基于用户ID）
    public static String generateToken(int userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        SecretKey key = getSecretKey();

        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // 存储用户ID
                .setIssuedAt(now) // 签发时间
                .setExpiration(expiration) // 过期时间
                .signWith(key, SignatureAlgorithm.HS256) // 签名算法
                .compact();
    }

    // 验证token并返回用户ID
    public static Integer verifyToken(String token) {
        try {
            SecretKey key = getSecretKey();
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            return null; // 验证失败返回null
        }
    }

    // 生成密钥
    private static SecretKey getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
    }
}