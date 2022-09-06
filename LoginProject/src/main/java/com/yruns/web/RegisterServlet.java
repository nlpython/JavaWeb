package com.yruns.web;

import com.yruns.pojo.User;
import com.yruns.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        // 判断验证码
        String u_checkCode = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        if (!u_checkCode.equalsIgnoreCase(checkCode)) {
            // 不允许注册
            request.setAttribute("register_msg", "验证码错误！");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // 2.4 执行方法
        // 判断用户名是否存在
        boolean isExist = userService.register(user);
        if (isExist) {
            // 用户不存在

            request.setAttribute("register_msg", "注册成功，请登录！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            // 用户已存在
            request.setAttribute("register_msg", "用户名已存在！");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
