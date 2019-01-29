package com.huayue.springsecuritydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/21.
 */
@RestController
@Slf4j
@RequestMapping("home")
public class HomeController {
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }
}
