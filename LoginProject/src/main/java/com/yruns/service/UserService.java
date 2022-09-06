package com.yruns.service;

import com.yruns.mapper.UserMapper;
import com.yruns.pojo.User;
import com.yruns.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * UserService
 */
public class UserService {
    // 加载mybatis的核心配置文件，获取SqlSessionFactory
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<User> login(String username, String password) {
        // 1.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2.获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selctByUsernamePassword(username, password);
        sqlSession.close();

        return users;
    }

    public User selectByUsername(String username) {
        // 1.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2.获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByUsername(username);
        sqlSession.close();

        return user;
    }

    public void insert(User user) {
        // 1.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2.获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.insert(user);
        sqlSession.commit();
        sqlSession.close();
    }

    public void insert(String username, String password) {
        // 1.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2.获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.insert(username, password);
        sqlSession.commit();
        sqlSession.close();
    }

    /*
     *  注册
     */
    public boolean register(User user) {
        // 1.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2.获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User u = userMapper.selectByUsername(user.getUsername());
        if (u == null) {
            // 用户名不存在
            userMapper.insert(user);
            sqlSession.commit();
            sqlSession.close();
        }

        return u == null;
    }

    /**
     *  select user
     */
    public boolean isExistUser(String username) {
        // 1.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2.获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User u = userMapper.selectByUsername(username);

        return u != null;
    }

}
