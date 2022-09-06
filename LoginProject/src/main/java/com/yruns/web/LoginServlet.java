package com.yruns.web;

import com.mysql.cj.Session;
import com.yruns.pojo.User;
import com.yruns.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收用户名和密码，是否记住密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String isRemember = request.getParameter("remember");
        // 2.4 调用Service查询
        List<User> users = userService.login(username, password);

        System.out.println(username);
        response.setContentType("text/html;charset=utf-8");
        // 3. 判断users是否为空
        if (users.size() != 0) {
            // 登录成功，跳转到查询所有的User路径
            if ("1".equals(isRemember)) {
                // 用户勾选了记住
                // 1.创建Cookie
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                // 2.发送
                c_username.setMaxAge(60*60*24*7);
                c_password.setMaxAge(60*60*24*7);
                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            // 将登录成功后的user对象存储到session  (实现跳转后 xxx,欢迎您)
            HttpSession session = request.getSession();
            session.setAttribute("user", users.get(0));

            response.sendRedirect("/selectAllServlet");
        } else {
            // 登录失败
            // 存储错误信息
            request.setAttribute("login_msg", "用户名或密码错误");
            // 跳转到login.jsp
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
