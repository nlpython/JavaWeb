package com.yruns.mapper;

import com.yruns.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selctByUsernamePassword(@Param("username")String username, @Param("password")String password);

    User selectByUsername(@Param("username")String username);

    void insert(@Param("username")String username, @Param("password")String password);

    void insert(User user);
}
