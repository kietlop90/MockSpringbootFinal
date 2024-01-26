package com.mock.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Component
public class AspectAppLogger {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.mock.api.controller.*.*(..))")
    public void beforeControllerMethod(JoinPoint jp) {
        putInformationLog("Start ", jp);
    }

    @After("execution(* com.mock.api.controller.*.*(..))")
    public void afterControllerMethod(JoinPoint jp) {
        putInformationLog("End ", jp);
    }

    @Before("execution(* com.mock.api.service.*.*(..))")
    public void beforeMethod(JoinPoint jp) {
        putInformationLog("Start ", jp);
    }

    @After("execution(* com.mock.api.service.*.*(..))")
    public void afterMethod(JoinPoint jp) {
        putInformationLog("End ", jp);
    }

    @Before("execution(* com.mock.api.repository.*.*(..))")
    public void beforeRepositoryMethod(JoinPoint jp) {
        putInformationLog("Start ", jp);
    }

    @After("execution(* com.mock.api.repository.*.*(..))")
    public void afterRepositoryMethod(JoinPoint jp) {
        putInformationLog("End ", jp);
    }

    private void putInformationLog(String startOrEnd, JoinPoint jp) {
        logger.info(startOrEnd + ":" + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

}
