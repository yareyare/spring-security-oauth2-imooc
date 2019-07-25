/**
 * Copyright
 */
package com.ivy.security.core.valiate;

import org.springframework.security.core.AuthenticationException;

/**
 * @author ivy on 2019-07-25.
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
