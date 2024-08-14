package com.example.demo.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    /*
    @Around("execution(* com.example.demo.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startTime;

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
    */
    // Before Advice
    @Before("execution(* com.example.demo.service.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Before method: " + joinPoint.getSignature());
    }

    // After Advice
    @After("execution(* com.example.demo.service.*.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("After method: " + joinPoint.getSignature());
    }

    // After Returning Advice
    @AfterReturning(pointcut = "execution(* com.example.demo.service.*.*(..))", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        System.out.println("After returning method: " + joinPoint.getSignature() + ", Result: " + result);
    }

    // After Throwing Advice
    @AfterThrowing(pointcut = "execution(* com.example.demo.service.*.*(..))", throwing = "error")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable error) {
        System.out.println("After throwing method: " + joinPoint.getSignature() + ", Exception: " + error);
    }

    // Around Advice
    @Around("execution(* com.example.demo.service.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around before method: " + joinPoint.getSignature());
        Object result = joinPoint.proceed(); // Proceed with the method invocation
        System.out.println("Around after method: " + joinPoint.getSignature());

        long startTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startTime;

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return result;
    }
}

