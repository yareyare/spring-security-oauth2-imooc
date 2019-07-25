/**
 * Copyright
 */
package com.ivy.security.core.valiate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ivy on 2019-07-25.
 */
public interface ValidateCodeGenerator {

    /**
     * 图片验证码生成
     *
     * @param request
     * @return
     */
    ImageCode createImageCode(HttpServletRequest request);
}
