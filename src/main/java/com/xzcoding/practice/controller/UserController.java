package com.xzcoding.practice.controller;

import com.xzcoding.practice.entity.User;
import com.xzcoding.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/select/{id}")
    public String select(@PathVariable int id){
        return userService.select(id).toString();
    }

    @PostMapping("/insert")
    public int insert(@RequestBody User user){
        return userService.insert(user);
    }

    @PutMapping("/update")
    public int update(@RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping("/delete/{id}")
    public int update(@PathVariable String id){
        return userService.delete(id);
    }
}