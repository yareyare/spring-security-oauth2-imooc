/**
 * Copyright
 */
package com.ivy.security.controller;

import com.ivy.security.exception.UserNotExistException;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ivy on 2019-07-14.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handleUserNotExistException(UserNotExistException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        return result;
    }

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initWebBinder(WebDataBinder binder) {
        //对日期的统一处理
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
        //添加对数据的校验
        //binder.setValidator();
    }
}
