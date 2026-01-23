package com.tlias.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class TestAop002 {
    @Pointcut("execution(* com.tlias.controller.*.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    //@Before("execution(* com.tlias.controller.*.*(..))")
    public void beforeFromAop002() {
        System.out.println("before from Aop002");
    }

    @After("pointCut()")
    public void afterFromAop002() {
        System.out.println("after from Aop002");
    }

    @AfterReturning("@annotation(com.tlias.aop.MyLog)")
    public  void afterreturningFromAop002() {
        System.out.println("after return from Aop002");
    }

}
