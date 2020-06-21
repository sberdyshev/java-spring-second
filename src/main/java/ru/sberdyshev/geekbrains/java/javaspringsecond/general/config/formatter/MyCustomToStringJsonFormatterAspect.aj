package ru.sberdyshev.geekbrains.java.javaspringsecond.general.config.formatter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public aspect MyCustomToStringJsonFormatterAspect {
//    @Around("@annotation(ru.sberdyshev.geekbrains.java.javaspringsecond.general.config.formatter.MyCustomToStringJson) && execution(* toString(..))")
    @Around("@annotation(ru.sberdyshev.geekbrains.java.javaspringsecond.general.config.formatter.MyCustomToStringJson) && execution(* *(..))")
    public String changeToStringImplementation(ProceedingJoinPoint pjp) throws Throwable {
        // start stopwatch
        String retVal = (String) pjp.proceed();
        System.out.println("this is an aspect");
        // stop stopwatch
        return retVal;
    }
}
