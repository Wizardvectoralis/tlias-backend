package com.tlias;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    //这就是用Jwts.SIG.HS256.key().创建秘钥并用code64编码的结果，所有二进制数据都用64个可打印字符表示
    public final String base64EncodedSecret_Key="E5zWDoKLEdI+0D+X6mVNxO1bk+rf/ms3soZWb2z1ig8=";

    //设置秘钥，至少32个字符且至少256位（utf编码一个英语字母占8位）但githubjjwt不建议用getbytes方法
    //private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("wangleileishizhu123Kwangleileishizhu123Kwangleileishizhu123K".getBytes(StandardCharsets.UTF_8));
    //设置过期时间，单位：毫秒
    public static final long EXPIRATION_TIME = 3600*1000L;

    //生成令牌
    @Test
    void  generateJWT(){
        //github建议按照以下代码自动创建一个SecretKey对象，然后用base64编码保存为一个可打印的字符串
        //SecretKey Secret_Key = Jwts.SIG.HS256.key().build();//等价于SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        //String secretKeyString = Encoders.BASE64.encode(Secret_Key.getEncoded());

        //自己创建一个map对象用于稍后装进JWT令牌的载荷
        Map<String,Object> myClaims=new HashMap<>();
        myClaims.put("name1","zhangsan1");
        String jwt = Jwts.builder()
                .header()
                .keyId("myKeyId")//可以通过add在令牌头添加键值对但是不建议
                .and()
                .issuer("wanglei")
                .subject("wanglei001")
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .notBefore(new Date(System.currentTimeMillis()+6000L))
                .claims(myClaims)
                .claim("name2","欣崽")
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedSecret_Key)))//只接受SECRET_KEY变量，会根据SECRET_KEY自动设置令牌头中的alg，并自动决定加密方式：For example, if you call signWith with a SecretKey that is 256 bits (32 bytes) long, it is not strong enough for HS384 or HS512, so JJWT will automatically sign the JWT using HS256.
                .compact();
        System.out.println("jwt is"+jwt);

    }

    //解析令牌
    @Test
    void readJWT(){
        Jwt<?,?> jwt;
        jwt=Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedSecret_Key)))//这里Constant Parsing Key目录：如果用secretKey加密，就用secretKey解；如果用privateKey加密，就应该用publicKey解
                .build()
                .parseSignedClaims("eyJraWQiOiJteUtleUlkIiwiYWxnIjoiSFMyNTYifQ.eyJpc3MiOiJ3YW5nbGVpIiwic3ViIjoid2FuZ2xlaTAwMSIsImV4cCI6MTc2ODQ5NDM5MiwibmJmIjoxNzY4NDkwNzk4LCJuYW1lMSI6InpoYW5nc2FuMSIsIm5hbWUyIjoi5qyj5bS9In0.jlYIPMapnJCT2cfSq4tR-0732VON87NxbflM7NeCUWk");//接收参数：jwtstring
        System.out.println(jwt);
    }
}
