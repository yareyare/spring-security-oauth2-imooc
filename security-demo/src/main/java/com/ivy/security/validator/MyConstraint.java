/**
 * Copyright
 */
package com.ivy.security.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author ivy on 2019-07-14.
 */
@Target(value = {ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
    // 必须有以下三个属性

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}