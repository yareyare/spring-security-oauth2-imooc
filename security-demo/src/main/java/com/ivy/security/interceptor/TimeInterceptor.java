/**
 * Copyright
 */
package com.ivy.security.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ivy on 2019-07-14.
 */
@Slf4j
@Component //除了声明为COmponent之外还需要@Configuration
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
        long startTime = (long) request.getAttribute("startTime");
        log.info("time interceptor 耗时：{}", System.currentTimeMillis() - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
        long startTime = (long) request.getAttribute("startTime");
        log.info("time interceptor 耗时：{}", System.currentTimeMillis() - startTime);
        log.info("ex ==== {}", ex);
    }
}
