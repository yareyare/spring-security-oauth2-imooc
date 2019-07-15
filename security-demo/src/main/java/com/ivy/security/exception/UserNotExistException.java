/**
 * Copyright
 */
package com.ivy.security.exception;

import lombok.Data;

/**
 * @author ivy on 2019-07-14.
 */
@Data
public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer id;

    public UserNotExistException() {
        super("user not exist");
        this.id = id;
    }
}
