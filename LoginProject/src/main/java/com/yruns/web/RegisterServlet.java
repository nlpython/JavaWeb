package com.yruns.web;

import com.yruns.mapper.UserMapper;
import com.yruns.pojo.User;
import com.yruns.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2.1 加载mybatis的核心配置文件，获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        // 2.2 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2.3 获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        // 2.4 执行方法
        // 判断用户名是否存在
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            // 用户已存在
            writer.write("用户已存在！");
        } else {
            // 用户不存在
            userMapper.insert(username, password);
            sqlSession.commit();
            writer.write("注册成功！");
        }

        // 2.5 释放资源
        sqlSession.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
