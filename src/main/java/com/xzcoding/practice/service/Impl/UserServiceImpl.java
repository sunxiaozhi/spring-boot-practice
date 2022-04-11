package com.xzcoding.practice.service.Impl;

import cn.hutool.core.util.IdUtil;
import com.xzcoding.practice.entity.User;
import com.xzcoding.practice.mapper.UserMapper;
import com.xzcoding.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> selectList() {
        return userMapper.selectList();
    }

    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public int insert(User user) {
        user.setId(IdUtil.simpleUUID());
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int delete(String id) {
        return userMapper.delete(id);
    }
}