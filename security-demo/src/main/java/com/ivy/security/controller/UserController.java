/**
 * Copyright
 */
package com.ivy.security.controller;

import com.ivy.security.dto.User;
import com.ivy.security.dto.UserCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ivy on 2019-07-11.
 */
@Slf4j
@RestController
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public List<User> getUser(@RequestParam(name="username",required = false, defaultValue = "ivy") String name){
    public List<User> getUser(UserCondition userCondition, @PageableDefault(page = 2, size = 17, sort = "username", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info(ReflectionToStringBuilder.toString(userCondition, ToStringStyle.MULTI_LINE_STYLE));
        log.info(pageable.getPageSize() + "");
        log.info(pageable.getPageNumber() + "");
        log.info(pageable.getSort().toString());
        List<User> users = new ArrayList<>();
        users.add(new User("ivy", "123456"));
        users.add(new User("doudou", "123456"));
        users.add(new User("diandian", "123456"));
        return users;
    }
}
