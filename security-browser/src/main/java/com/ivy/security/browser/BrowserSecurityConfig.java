/**
 * Copyright
 */
package com.ivy.security.browser;

import com.ivy.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.ivy.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ivy on 2019-07-22.
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//        http.httpBasic()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(myAuthenticationSuccessHandler)
                .and()

                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated() //需要认证
                .and()
                .csrf().disable(); // 302错误，先关闭csrf检查
    }

    //    @Override
    protected void configure1(HttpSecurity http) throws Exception {
        http.formLogin()
//        http.httpBasic()
                .loginPage("/imooc-login.html")
                .loginProcessingUrl("/authentication/form")
                .and()

                .authorizeRequests()
                .antMatchers("/imooc-login.html").permitAll()
                .anyRequest()
                .authenticated() //需要认证
                .and()
                .csrf().disable(); // 302错误，先关闭csrf检查
    }
}
