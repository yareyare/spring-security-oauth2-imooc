/**
 * Copyright
 */
package com.ivy.security.controller.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author ivy on 2019-07-19.😀
 */

@EnableSwagger2
@Configuration
@Slf4j
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringFoxDocket(SwaggerProperties swaggerProperties) {
        log.info("Starting Swagger ! \uD83D\uDE00");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Contact contact = new Contact(
                swaggerProperties.getContactName(),
                swaggerProperties.getContactUrl(),
                swaggerProperties.getContactEmail());

        ApiInfo apiInfo = new ApiInfo(
                swaggerProperties.getTitle(),
                swaggerProperties.getDescription(),
                swaggerProperties.getVersion(),
                swaggerProperties.getTermsOfServiceUrl(),
                contact,
                swaggerProperties.getLicense(),
                swaggerProperties.getLicenseUrl(),
                new ArrayList<>()
        );

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .directModelSubstitute(ByteBuffer.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .select()  //RequestHandlerSelectors.any()，所有的路劲都去扫描
                //这个扫描包的意思一样，固定路劲就去相应的路劲扫描
                //.paths(PathSelectors.any())
                .paths(regex(swaggerProperties.getDefaultIncludePattern()))
                .build();

        return docket;
    }

}
