package com.example.hello.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {
  @Around("execution(* hello.hellospring..*(..))") //원하는 적용 어디에다가 적용할지
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    System.out.println("START: " + joinPoint.toString());
    try {
      return joinPoint.proceed(); //호출이 될때마다
    } finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;
      System.out.println("END: " + joinPoint.toString()+ " " + timeMs +
              "ms");
    }
  }
}
