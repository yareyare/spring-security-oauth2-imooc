/**
 * Copyright
 */
package com.ivy.security.controller.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.util.Arrays;

/**
 * @author ivy on 2019-07-18.
 */
@Slf4j
//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.ivy.security.controller.UserController.*(..))")
    public void handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.info("=========time aspect begin==========");
        long startTime = System.currentTimeMillis();
        // 拦截器拿不到被拦截的方法的执行时参数，但是aspect可以
        Arrays.stream(pjp.getArgs()).forEach(arg -> {
            log.info("方法参数" + arg);
        });
        Object proceed = pjp.proceed();

        log.info("=========time aspect 耗时 " + (System.currentTimeMillis() - startTime) + "==========");
        log.info("=========time aspect after==========");
    }

}
