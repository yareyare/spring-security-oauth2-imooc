/**
 * Copyright
 */
package com.ivy.security.core;

import com.ivy.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ivy on 2019-07-23.
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreSecurity {
}
