package com.yruns.jdbc;

import java.sql.*;

/**
 * Jdbc_PreparedStatement
 */
public class Jdbc_PreparedStatement {
    public static void main(String[] args) throws Exception {
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc?serverTimezone=UTC";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 3. 定义sql
        String sql = "select * from student where s_name=?";
        // 4. 获取执行sql的对象PreparedStatement
        PreparedStatement pps = conn.prepareStatement(sql);
        // 设置？的值
        pps.setString(1, "zhangsan");
        // 5. 执行sql
        ResultSet rs = pps.executeQuery(); // 不再需要传入sql

        // 6. 处理结果
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int age = rs.getInt(3);
            System.out.println("ID: " + id + ", name: " + name + ", age: " + age);
        }
        // 7. 释放资源
        pps.close();
        conn.close();
    }
}
