/**
 * Copyright
 */
package com.ivy.security.controller.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ivy on 2019-07-14.
 */
@Slf4j
//@Component  //第一种filter生效方式，另一种是用@Configuration  参考：WebConfig.java
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("【fileter.init】time filter init");
    }

    @Override
    public void destroy() {
        log.info("【fileter.destroy】time filter destory");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("【fileter.doFilter】time filter start");
        long start = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        log.info("【fileter.doFilter】timeFilter 耗时：{}", System.currentTimeMillis() - start);
        log.info("【fileter.doFilter】time filter finish");

    }
}
