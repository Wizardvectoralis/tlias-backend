package com.tlias;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTestFormal {

    //第一步：创建一个SecretKey对象，然后用base64编码保存为一个可打印的字符串
    SecretKey Secret_Key = Jwts.SIG.HS256.key().build();
    String secretKeyString = Encoders.BASE64.encode(Secret_Key.getEncoded());

    //将上述secretKeyString重新命名为base64EncodedSecret_Key，就是下面的字符串
    public final String base64EncodedSecret_Key="E5zWDoKLEdI+0D+X6mVNxO1bk+rf/ms3soZWb2z1ig8=";

    //设置过期时间，单位：毫秒
    public static final long EXPIRATION_TIME = 3600*1000L;

    //生成令牌
    @Test
    void  generateJWT(){

        //自己创建一个map对象用于稍后装进JWT令牌的载荷部分
        Map<String,Object> myClaims=new HashMap<>();
        myClaims.put("name1","zhangsan1");
        String jwt = Jwts.builder()
                .header()                           //开始设置令牌头
                .keyId("myKeyId")               //不是必须的
                .and()                              //退出设置令牌头
                .issuer("wanglei")                  //该 JWT 令牌是由哪个主体（系统、服务、组织等）签发的
                .subject("wanglei001")              //该JWT令牌是给谁的，最好是用户id等唯一标识
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))  //过期时间
                .notBefore(new Date(System.currentTimeMillis()+6000L))             //生效时间
                .claims(myClaims)                                                  //将自定义内容放进载荷
                .claim("name2","欣崽")                                   //将自定义单个键值对放进载荷
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedSecret_Key)))
                //signWith接受SECRET_KEY变量，会根据SECRET_KEY自动设置令牌头中的alg，并自动决定加密方式
                //Keys.hmacShaKeyFor接收一个字节数组作为参数，Decoders.BASE64.decode()把一个base64编码的字符串转换为字节数组
                .compact();
        System.out.println("jwt is："+jwt);

    }

    //解析令牌
    @Test
    void readJWT(){
        Jwt<?,?> jwt;   //声明一个jwt变量
        jwt=Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedSecret_Key)))    //接收一个SecretKey变量
                .build()
                .parseSignedClaims("eyJraWQiOiJteUtleUlkIiwiYWxnIjoiSFMyNTYifQ.eyJpc3MiOiJ3YW5nbGVpIiwic3ViIjoid2FuZ2xlaTAwMSIsImV4cCI6MTc2ODQ5NDM5MiwibmJmIjoxNzY4NDkwNzk4LCJuYW1lMSI6InpoYW5nc2FuMSIsIm5hbWUyIjoi5qyj5bS9In0.jlYIPMapnJCT2cfSq4tR-0732VON87NxbflM7NeCUWk");//接收参数：jwtstring
       //parseSignedClaims接收一个jwt令牌作为参数


        System.out.println(jwt);

    }
}
