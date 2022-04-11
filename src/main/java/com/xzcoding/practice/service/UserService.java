package com.xzcoding.practice.service;

import com.xzcoding.practice.entity.User;

import java.util.List;

/**
 * User服务接口
 *
 * @author sunxiaozhi
 * @date 2022-04-11 15:31
 */
public interface UserService {
    List<User> selectList();

    User selectById(int id);

    int insert(User user);

    int update(User user);

    int delete(String id);
}
