package com.tlias.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect    //标记这是一个切面类
@Component
public class TimeLogAspect {

    //配置通知
    @Around("execution(* com.tlias.controller.*.*(..))")
    public Object countMethodsTime(ProceedingJoinPoint joinPoint) throws Throwable {

        //获取程序开始时间
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info(joinPoint.getSignature()+ "耗时{} ms", (end - begin));
        return result;
    }

}
