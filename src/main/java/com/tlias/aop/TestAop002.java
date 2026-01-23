package com.tlias.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAop002 {
    @Pointcut("execution(* com.tlias.controller.*.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void beforeFromAop002() {
        System.out.println("before from Aop002");
    }

    @After("pointCut()")
    public void afterFromAop002() {
        System.out.println("after from Aop002");
    }
}
