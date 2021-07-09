package com.midasin.spr.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    private Logger log = Logger.getLogger(LogAspect.class);
    
    /*@Around("execution(* org.mybatis.spring.SqlSessionTemplate.*(String, ..))")*/
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("TRIGGERD");
        Object proceed = joinPoint.proceed();
        return proceed;
    }
}
