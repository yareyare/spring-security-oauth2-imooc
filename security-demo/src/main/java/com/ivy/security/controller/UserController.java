/**
 * Copyright
 */
package com.ivy.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivy.security.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * @author ivy on 2019-07-11.
 */
@Slf4j
@RestController
@RequestMapping
public class UserController {

    //BindingResult 要结合 @Valid 一起使用
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user, BindingResult errors) throws JsonProcessingException {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> log.info(error.toString()));
        }
        log.info(user.getBirthday().toString());
        log.info(new ObjectMapper().writeValueAsString(user));
        user.setId(1);
        return user;
    }

    @PutMapping("/users/{id:\\d+}")
    public User updateUser(@Valid @RequestBody User user, BindingResult errors) throws JsonProcessingException {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
                        FieldError fieldError = (FieldError) error;
                        String message = fieldError.getField() + " =>" + fieldError.getDefaultMessage();
                        log.info(message);
                    }
            );
        }
        log.info(new ObjectMapper().writeValueAsString(user));
        return user;
    }
//
//    @JsonView(User.UserSimpleView.class)
//    @GetMapping(value = "/user")
////    public List<User> getUser(@RequestParam(name="username",required = false, defaultValue = "ivy") String name){
//    public List<User> getUser(UserCondition userCondition, @PageableDefault(page = 2, size = 17, sort = "username", direction = Sort.Direction.ASC) Pageable pageable) {
//        log.info(ReflectionToStringBuilder.toString(userCondition, ToStringStyle.MULTI_LINE_STYLE));
//        log.info(pageable.getPageSize() + "");
//        log.info(pageable.getPageNumber() + "");
//        log.info(pageable.getSort().toString());
//        List<User> users = new ArrayList<>();
//        users.add(new User(1, "ivy", "123456", new Date()));
//        users.add(new User(2, "doudou", "123456", new Date()));
//        users.add(new User(3, "diandian", "123456", new Date()));
//        return users;
//    }

    @JsonView(User.UserDetailView.class)
    @GetMapping(value = "/users/{id:\\d+}")
    public User getUser(@PathVariable String id) {

        //抛出异常后，interceptor postHandle() 将不会执行
//        throw new UserNotExistException();

        log.info("进入 getUser 服务");

        User user = new User();
        user.setUsername("ivy");
        user.setPassword("123456");
        return user;
    }

    @DeleteMapping(value = "/users/{id:\\d+}")
    public void deleteUser(@PathVariable String id) {
        log.info("id:{}", id);
    }
}
