package com.yruns.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * ServletDemo2
 */
@WebServlet("/demo2")
public class ServletDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // getMethod()：获取请求方式
        String method = req.getMethod();
        System.out.println(method);        // GET

        // getContextPath()：获取虚拟目录
        String contextPath = req.getContextPath();
        System.out.println(contextPath);   //

        // getRequestURL()：获取URL
        String requestURL = req.getRequestURI();
        System.out.println(requestURL);    //

        // getRuquestURI()：获取URI
        String requestURI = req.getRequestURI();

        // getQueryString()：获取请求参数
        String queryString = req.getQueryString();
        System.out.println(queryString);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取POST请求体
        // 1.获取字符输入流
        BufferedReader reader = req.getReader();
        // 2.读取数据
        String line = reader.readLine();
        System.out.println(line);   // username=zhangsan&password=123
    }
}
