package com.yruns.web.cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/AServlet")
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 发送Cookie
        // 1.创建Cookie对象
        String value = "张三";
        value = URLEncoder.encode(value, "UTF-8");  // %E5%BC....
        Cookie cookie = new Cookie("username", value);
        // 2. 发送Cookie
        cookie.setMaxAge(60*60*24); // 设置Cookie存活时间
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
