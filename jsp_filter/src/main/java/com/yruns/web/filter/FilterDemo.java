package com.yruns.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * FilterDemo
 */
//@WebFilter("/*")
public class FilterDemo implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 方形前，对request数据进行处理
        System.out.println("FilterDemo before...");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
        // 放行后，对response数据进行处理
        System.out.println("FilterDemo after...");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
