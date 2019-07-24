/**
 * Copyright
 */
package com.ivy.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivy.security.core.properties.LoginType;
import com.ivy.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ivy on 2019-07-24.
 *
 * SavedRequestAwareAuthenticationSuccessHandler spring 默认的认证成功处理方式
 */
@Slf4j
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("【MyAuthenticationHandler.onAuthenticationSuccess()】: 登录成功");

        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }


    }
}
