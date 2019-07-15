/**
 * Copyright
 */
package com.ivy.security.service.impl;

import com.ivy.security.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author ivy on 2019-07-14.
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greeting(String name) {
        System.out.println("greeting method impl     hello:" + name);
        return "hello:" + name;
    }
}
