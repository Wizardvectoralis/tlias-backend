package com.tlias.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)       //自定义的注解可以作用在哪些java元素上，方法上还是类上
@Retention(RetentionPolicy.RUNTIME)        //定义注解保留到哪个阶段

public @interface MyLog {                   //自定义一个注解，名字随便,然后就可以使用自己的注解了
}
