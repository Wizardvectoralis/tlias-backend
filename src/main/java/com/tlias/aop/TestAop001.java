package com.tlias.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class TestAop001 {
    @Pointcut("execution(* com.tlias.controller.*.*(..))")
    //切入controller包下的所有类的所有方法
    //返回值和包名之间必须有空格,切入点最好是接口而不是实现类
    public void pointCut() {
    }

    @Before("pointCut()")   //前置通知：数字越小先执行;
    public void beforeFromAop001() {
        System.out.println("before from Aop001");
    }

    @After("pointCut()")     //后置通知：数字越小越后执行
    public void afterFromAop001() {
        System.out.println("after from Aop001");
    }



}
