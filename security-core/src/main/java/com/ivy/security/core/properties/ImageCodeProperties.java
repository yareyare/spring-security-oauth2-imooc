package com.ivy.security.core.properties;

import lombok.Data;

/**
 * @author ivy
 * 应用级 -- 默认配置
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    private int width = 80;

    private int height = 45;

    private int length = 4;

    /**
     * 60秒超时
     */
    private int expireIn = 60;

    private int fontSize = 40;

    private String url;

    public ImageCodeProperties() {
        setLength(4);
    }

}
