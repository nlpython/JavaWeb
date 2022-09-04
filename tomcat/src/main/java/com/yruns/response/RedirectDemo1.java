package com.yruns.response;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/resp1")
public class RedirectDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("There is Demo1...");

        // 重定向
//        // 1.设置响应状态码 302
//        response.setStatus(302);
//        // 2.设置响应头 Location
//        response.setHeader("Location", "resp2");

        // 简化方式重定向
        response.sendRedirect("resp2");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
