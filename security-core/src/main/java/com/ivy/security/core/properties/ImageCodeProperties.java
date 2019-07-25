package com.ivy.security.core.properties;

import lombok.Data;

/**
 * @author ivy
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {
    private int width = 80;
    private int height = 45;
    private int fontSize = 40;

    public ImageCodeProperties() {
        setLength(4);
    }

}
