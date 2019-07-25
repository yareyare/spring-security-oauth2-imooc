/**
 * Copyright
 */
package com.ivy.security.code;

import com.ivy.security.core.valiate.ImageCode;
import com.ivy.security.core.valiate.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ivy on 2019-07-25.
 */
@Slf4j
@Component("imageCodeGenerator1")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode createImageCode(HttpServletRequest request) {
        log.info("更高级的可配置的验证码生成逻辑！");
        return null;
    }
}
