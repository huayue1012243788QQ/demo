package com.huayue.springsecuritydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/21.
 */
@RestController
@Slf4j
@RequestMapping("index")
public class IndexController {
    @GetMapping("hello")
    public String hello() {
        return "hello index";
    }
}
