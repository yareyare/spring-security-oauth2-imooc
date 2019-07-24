/**
 * Copyright
 */
package com.ivy.security.core.properties;

import lombok.Data;

/**
 * @author ivy on 2019-07-23.
 */
@Data
public class BrowserProperties {

    /**
     * 指定默认值
     */
    private String loginPage = "/imooc-login.html";

    private LoginType loginType = LoginType.JSON;
}
