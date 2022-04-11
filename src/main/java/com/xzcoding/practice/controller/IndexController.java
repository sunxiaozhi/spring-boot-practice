package com.xzcoding.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Index控制器
 *
 * @author sunxiaozhi
 * @date 2022/4/10 21:19
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
