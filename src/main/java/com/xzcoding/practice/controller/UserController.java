package com.xzcoding.practice.controller;

import com.xzcoding.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User控制器
 *
 * @author sunxiaozhi
 * @date 2022-04-11 15:31
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getUser/{id}")
    public String getUser(@PathVariable int id){
        return userService.select(id).toString();
    }
}