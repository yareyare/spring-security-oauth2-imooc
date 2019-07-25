/**
 * Copyright
 */
package com.ivy.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ivy on 2019-07-23.
 */

@Data
@ConfigurationProperties(prefix = "demo.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

}
