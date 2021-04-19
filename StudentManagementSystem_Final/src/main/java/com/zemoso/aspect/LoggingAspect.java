package com.zemoso.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private static Logger myLogger=Logger.getLogger(LoggingAspect.class.getName());
    @After("execution(* com.zemoso.service.*.*(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        String method=joinPoint.getSignature().toShortString();
        myLogger.info("--> executing @After (finally) on method: "+method);

    }

    @AfterThrowing(pointcut = "execution(* com.zemoso.service.*.*(..))",
            throwing="exc")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint,Throwable exc)
    {
        String method=joinPoint.getSignature().toShortString();
        myLogger.info("--> executing @AfterThrowing on method: "+method);

        myLogger.info("-->The Exception is : "+exc);

    }

    @Before("execution(* com.zemoso.service.*.*(..))")
    public void beforeAddAccountAdvice(JoinPoint joinPoint)
    {
        myLogger.info("-->Executing  before advice method");
        //display the method signature
        MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();

        myLogger.info("Method : "+methodSignature);

    }

}
