package com.tlias;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;


import javax.crypto.SecretKey;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TempTest {
    @Test
    void contextLoads() {
        String name="012.456.89";
        String substring = name.substring(name.lastIndexOf("."));
        System.out.println(substring);

        String substring1 = name.substring(1, 7);
        System.out.println(substring1);
    }
    //设置秘钥，至少32个字符且至少256位（utf编码一个英语字母占8位）
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("wangleileishizhu123Kwangleileishizhu123Kwangleileishizhu123K".getBytes(StandardCharsets.UTF_8));

    //设置过期时间，单位：毫秒
    public static final long EXPIRATION_TIME = 3600*1000L;

    //以下代码测试生成和校验JWT令牌
    @Test
    void generateJWT1() {

        Map<String,Object> claims=new HashMap<>();
        Map<String,Object> anyMap= Map.of("hobby","pingpong");
        claims.put("name","zhangsan");
        claims.put("character","employee");

        //可以设置一些令牌头参数,既可以用封装好的现成的令牌头，也可以用add方法自定义令牌头
        /*通过content或者claims方法（二选一不可同时）设置一些载荷，载荷可以是能转换为字节数组的任意内容例如图片文字
        claims分两种：官方标准claim字段例如expiration，notBefore,也可以自定义claim
         */
        //调用 signWith 或 encryptWith 方法对 JWT 进行数字签名或加密，
        //
        //调用compact方法最终生成
        String jwt= Jwts
                .builder()
                        .header()//进入令牌头部配置模式
                .keyId("aKeyId")//向已经封装好的头部的kid键赋值“aKeyId”，当
                .x509Url(URI.create("aurl"))//给头部实际键名x5u赋值
                .add("address","baotou")//自定义键值对添加到令牌头
                .add(anyMap)//自定义map对象到令牌头,不建议，最好放到载荷中
                .and()//退出令牌头配置，开始配置载荷

                //.content("mynameiswanglei".getBytes(StandardCharsets.UTF_8),"text/plain")
                .issuer("me")//令牌的签发者
                .subject("wanglei001")//令牌的主体
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .notBefore(new Date(System.currentTimeMillis()+3000L))
                .claim("zhangsan","name")//添加单个键值对到载荷
                .claims(claims)//添加一个map集合到载荷
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
        System.out.println(jwt);

//                claims().
//                and().content().compact().signWith(SignatureAlgorithm.HS256,SECRET_KEY).
//                add
//                compact();

    }

    //正式的生产jwt令牌方式
    @Test
    void  generateJWT(){
        Map<String,Object> myclaims=new HashMap<>();
        myclaims.put("name1","zhangsan");
        myclaims.put("name2","lisi");

        String jwt=Jwts.builder()
                .issuer("wanglei")//签发者，字符串
                .subject("wanglei001")//主体，字符串，一般为用户唯一标识符
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))//过期时间
                .notBefore(new Date(System.currentTimeMillis()+60000L))//生效时间
                .claims(myclaims)//自定义多个载荷，键值对
                .claim("name3","wangwu")//自定义单个载荷，键值对
                .signWith(SECRET_KEY,SignatureAlgorithm.HS256)//设置签名和算法，SecretKey对象
                .compact();//生成
        System.out.println(jwt);


    }

    @Test
    void readJWT(){
        Jws<Claims> jws=Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3YW5nbGVpIiwic3ViIjoid2FuZ2xlaTAwMSIsImV4cCI6MTc2ODQ3NDQ1NCwibmJmIjoxNzY4NDcwOTE0LCJuYW1lMiI6Imxpc2kiLCJuYW1lMSI6InpoYW5nc2FuIiwibmFtZTMiOiJ3YW5nd3UifQ.VXipuES8JfT6i749_XVXC0TLwtjEfGrWJwubMpIqX24");


        Claims claims=jws.getBody();
        System.out.println(claims.getIssuer());
        System.out.println(claims.getSubject());
        System.out.println(claims.getExpiration());
        System.out.println(claims.getNotBefore());

        System.out.println(claims.get("name1",String.class));


    }
}
