/**
 * Copyright
 */
package com.ivy.security.controller.config;

import com.ivy.security.controller.filter.TimeFilter;
import com.ivy.security.interceptor.TimeInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ivy on 2019-07-14.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer {

    //    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor);
    }

    //添加第三方的Filter
//    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new TimeFilter());

        List<String> urls = new ArrayList<>();
        urls.add("/*");

        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }

//    @Override
//	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//		configurer.registerCallableInterceptors(interceptors); // 设置Callable<T>形式的拦截器
//		configurer.registerDeferredResultInterceptors(interceptors); // 设置DeferredResult<T>形式的拦截器
//		configurer.setDefaultTimeout(timeout);	// 设置处理线程的默认超时时间
//		configurer.setTaskExecutor(taskExecutor); // 设置处理线程的线程池
//	}

}
