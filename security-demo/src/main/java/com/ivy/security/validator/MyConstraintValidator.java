/**
 * Copyright
 */
package com.ivy.security.validator;

import com.ivy.security.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ivy on 2019-07-14.
 */
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    /**
     * 校验器里可以注入spring容器中的任何东西
     */
    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        log.info(" my validator init() ");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        helloService.greeting("tom");
        log.info("[MyConstraintValidator.isValid()]" + value.toString());

        // 返回false，永远校验不通过 哈哈哈！
        return false;
    }
}
