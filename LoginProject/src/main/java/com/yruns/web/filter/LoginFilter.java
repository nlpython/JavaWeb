package com.yruns.web.filter;

import com.yruns.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  登录验证过滤器
 */

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        // 判断访问的资源路径是否和登录注册有关
        String[] urls = {"/login.jsp", "/imgs/", "/css/", "/loginServlet",
                "/register.jsp", "/registerServlet", "/checkCodeServlet", "/selectUserServlet"};

        String url = req.getRequestURL().toString();
        for (String u: urls) {
            if (url.contains(u)) {
                // 直接放行
                chain.doFilter(req, response);
                return;  // 再次回来后直接结束，不执行后续操作
            }
        }


        // 1.判断session中是否有user对象
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // 已经登录过，放行
            chain.doFilter(req, response);
        } else {
            // 未登录
            req.setAttribute("login_msg", "您尚未登录！");
            req.getRequestDispatcher("/login.jsp").forward(req, response);
        }

    }
}
