package com.huayue.springsecuritydemo.controller;

import com.huayue.springsecuritydemo.entity.User;
import com.huayue.springsecuritydemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/21.
 */
@RestController
@Slf4j
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public Object saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
