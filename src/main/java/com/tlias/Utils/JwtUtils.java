package com.tlias.Utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String base64EncodedSecretKey="E5zWDoKLEdI+0D+X6mVNxO1bk+rf/ms3soZWb2z1ig8=";


    //方法重载1：通过subject添加用户信息
    public static String generateToken(Map<String, Object> claims,String subject, long expiration){
        String jwt= Jwts.builder()
                .issuer("wanglei001")
                .subject(subject)
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .notBefore(new Date(System.currentTimeMillis()))
                .claims(claims)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedSecretKey)))
                .compact();

        return jwt;

    }

    //方法重载2：通过自定义map添加用户信息
    public static String generateToken(Map<String, Object> claims,long expiration){
        String jwt= Jwts.builder()
                .issuer("wanglei001")
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .notBefore(new Date(System.currentTimeMillis()))
                .claims(claims)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedSecretKey)))
                .compact();

        return jwt;

    }

    public static Jwt<?,?> parseJwt(String jwt){
        Jwt<?,?> jwt1=Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedSecretKey)))
                .build()
                .parseSignedClaims(jwt);
        return jwt1;

    }
}
