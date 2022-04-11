package com.xzcoding.practice.service.Impl;

import com.xzcoding.practice.entity.User;
import com.xzcoding.practice.mapper.UserMapper;
import com.xzcoding.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User服务
 *
 * @author sunxiaozhi
 * @date 2022-04-11 15:31
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User select(int id){
        return userMapper.select(id);
    }
}