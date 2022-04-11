package com.xzcoding.practice.mapper;

import com.xzcoding.practice.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * User-Mapper
 *
 * @author sunxiaozhi
 * @date 2022-04-11 15:31
 */
@Mapper
public interface UserMapper {
    User select(int id);

    int insert(User user);

    int update(User user);

    int delete(String id);
}