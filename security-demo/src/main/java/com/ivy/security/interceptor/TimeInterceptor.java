/**
 * Copyright
 */
package com.ivy.security.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ivy on 2019-07-14.
 */
@Slf4j
@Component //除了声明为Component之外还需要 @Configuration 见WebConfig.java
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //handler 对象只能拿到controller处理方法声明，却拿不到方法执行时真正的参数
        log.info("【TimeInterceptor.preHandle】preHandle");
        log.info(((HandlerMethod) handler).getBean().getClass().getName());
        log.info(((HandlerMethod) handler).getMethod().getName());
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("【TimeInterceptor.postHandle】postHandle");
        long startTime = (long) request.getAttribute("startTime");
        log.info("【TimeInterceptor.postHandle】time interceptor 耗时：{}", System.currentTimeMillis() - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("【TimeInterceptor.afterCompletion】afterCompletion");
        long startTime = (long) request.getAttribute("startTime");
        log.info("【TimeInterceptor.afterCompletion】time interceptor 耗时：{}", System.currentTimeMillis() - startTime);
        log.info("【TimeInterceptor.afterCompletion】ex ==== {}", ex);
    }
}
