package com.tn.esprit.gestionmagasinstock.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectConf {
    @Pointcut("execution(* com.tn.esprit.gestionmagasinstock.service.*.*(..))")
    public void serviceGetMethods() {

    }

    @Before("serviceGetMethods()")
    public void logMethod( JoinPoint joinPoint ) {

        String name = joinPoint.getSignature().getName();
        System.out.println("aspect thingyy " + name + " ");

    }
}
