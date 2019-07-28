/**
 * Copyright
 */
package com.ivy.security.core.valiate;

import com.ivy.security.core.properties.SecurityProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ivy on 2019-07-24.
 */
@Slf4j
@Data
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    @Autowired
    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("=============" + httpServletRequest.getRequestURI());
//        if (StringUtils.equalsIgnoreCase(httpServletRequest.getRequestURI(), "/authentication/form")
//                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")) {
        boolean action = false;

        for (String url : urls) {
            if (antPathMatcher.match(url, httpServletRequest.getRequestURI())) {
                action = true;
            }
        }

        if (action) {
            try {
                validateCode(new ServletWebRequest(httpServletRequest));
            } catch (AuthenticationException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private void validateCode(ServletWebRequest servletWebRequest) throws ValidateCodeException, ServletRequestBindingException {
        ImageCode imageCodeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateController.SESSION_KEY);

        String imageCodeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");

        if (StringUtils.isBlank(imageCodeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (imageCodeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (imageCodeInSession.isExpired()) {
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(imageCodeInRequest, imageCodeInSession.getCode())) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSION_KEY);
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
        if (configUrls != null && configUrls.length>0) {
            urls.addAll(Arrays.asList(configUrls));
        }
        urls.add("/authentication/form");
    }
}
