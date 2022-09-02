package com.yruns.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * com.yruns.jdbc.Jdbc_ResultSet
 */
public class Jdbc_ResultSet {
    public static void main(String[]args) throws Exception {
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取连接
        String url="jdbc:mysql://127.0.0.1:3306/jdbc?serverTimezone=UTC";
        String username="root";
        String password="root";
        Connection conn=DriverManager.getConnection(url,username,password);

        // 3. 定义sql
        String sql="select * from student;";
        // 4. 获取执行sql的对象Statement
        Statement stmt=conn.createStatement();
        // 5. 执行sql
        ResultSet rs = stmt.executeQuery(sql);

        // 6. 处理结果
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int age = rs.getInt(3);
            System.out.println("ID: " + id + ", name: " + name + ", age: " + age);
        }

        // 7. 释放资源
        rs.close();
        stmt.close();
        conn.close();
    }
}
