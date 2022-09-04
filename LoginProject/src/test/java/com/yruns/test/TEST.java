package com.yruns.test;

import com.yruns.mapper.UserMapper;
import com.yruns.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * TEST
 */
public class TEST {

    @Test
    public void testSelectByUsernamePassword() throws Exception {
        String username = "zhangsan";
        String password = "123";

        // 1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 4.执行方法
        List<User> users = userMapper.selctByUsernamePassword(username, password);
        System.out.println(users);

        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectByUsername() throws Exception {
        String username = "zhangsan";

        // 1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 4.执行方法
        User user = userMapper.selectByUsername(username);
        System.out.println(user);

        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void testInsert() throws Exception {
        User user = new User();
        String username = "bingjiu";
        String password = "456";
        user.setUsername(username);
        user.setPassword(password);

        // 1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 4.执行方法
        userMapper.insert(username, password);
        sqlSession.commit();

        // 5.释放资源
        sqlSession.close();
    }
}
