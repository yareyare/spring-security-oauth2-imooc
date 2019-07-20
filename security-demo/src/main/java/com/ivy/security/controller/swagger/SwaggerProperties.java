/**
 * Copyright
 */
package com.ivy.security.controller.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ivy on 2019-07-19.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private String title = "Application API";
    private String description = "API documentation";
    private String version = "0.0.1";
    private String termsOfServiceUrl;
    private String contactName;
    private String contactUrl;
    private String contactEmail;
    private String license;
    private String licenseUrl;
    private String defaultIncludePattern;

}
