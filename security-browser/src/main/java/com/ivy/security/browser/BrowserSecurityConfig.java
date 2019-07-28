/**
 * Copyright
 */
package com.ivy.security.browser;

import com.ivy.security.browser.authentication.MyAuthenticationFailureHandler;
import com.ivy.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.ivy.security.core.properties.SecurityProperties;
import com.ivy.security.core.valiate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author ivy on 2019-07-22.
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    /**
     * remember me 配置
     */
//    @Override
    protected void configure9(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
//        http.httpBasic()
                    .loginPage("/authentication/require")
                    .loginProcessingUrl("/authentication/form")
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(myAuthenticationFailureHandler)
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .authorizeRequests()
                    .antMatchers("/authentication/require",
                            "/error",
                            "/code/image",
                            securityProperties.getBrowser().getLoginPage()).permitAll()
                    .anyRequest()
                    .authenticated() //需要认证
                    .and()
                .csrf().disable(); // 302错误，先关闭csrf检查
    }

    /**
     * 自定义登录成功处理器和登录失败处理器
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
//        http.httpBasic()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()

                .authorizeRequests()
                .antMatchers("/authentication/require",
                        "/error",
                        "/code/image",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated() //需要认证
                .and()
                .csrf().disable(); // 302错误，先关闭csrf检查
    }

    //自定义登录页面和认证流程
    //    @Override
    protected void configure2(HttpSecurity http) throws Exception {
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

    //最简单的security配置
    //    @Override
    protected void configure1(HttpSecurity http) throws Exception {
        http.formLogin()
//        http.httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated() //需要认证
                .and()
                .csrf().disable(); // 302错误，先关闭csrf检查
    }
}
