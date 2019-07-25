package com.ivy.security.core.properties;

import lombok.Data;

@Data
public class SmsCodeProperties {

    private int length = 6;

    private int expireIn = 300;

    private String url;
}
