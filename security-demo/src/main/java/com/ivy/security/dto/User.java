/**
 * Copyright
 */
package com.ivy.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ivy on 2019-07-11.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private String username;
    private String password;
}
