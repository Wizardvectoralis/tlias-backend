package com.tlias;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TliasApplicationTests {


    @Test
    void contextLoads() {
        String name="012.456.89";
        //lastindexof：返回一个字符串中最后一个参数所在的序号
        //substring：截取字符串[参数1，参数2)或截取字符串[参数，字符串末尾)
        System.out.println(name.substring(2,8));
        System.out.println(name.substring(3));
        String substring = name.substring(name.lastIndexOf("."));
        System.out.println(substring);

    }



}
