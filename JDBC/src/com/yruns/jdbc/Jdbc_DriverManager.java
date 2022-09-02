package com.yruns.jdbc;

/**
 * JDBC详解：DriverManager
 */
public class Jdbc_DriverManager {
    public static void main(String[] args) throws Exception {
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取连接
        // 如果连接的是本机mysql服务器，并且使用3306端口，可以简写为jdbc:mysql:///jdbc
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc?serverTimezone=UTC";
    }
}
