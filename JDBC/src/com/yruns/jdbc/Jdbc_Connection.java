package com.yruns.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * com.yruns.jdbc.Jdbc_Connection
 */

public class Jdbc_Connection {
    public static void main(String[] args) throws Exception {
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc?serverTimezone=UTC";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 3. 定义sql
        String sql1 = "insert into student VALUES(2, 'lisi', 19);";
        String sql2 = "insert into student VALUES(3, 'wangwu', 17);";
        // 4. 获取执行sql的对象Statement
        Statement stmt = conn.createStatement();
        // 5. 执行sql 期望sql1和sql2同时执行
        try {
            // 开启事务
            conn.setAutoCommit(false);
            int count1 = stmt.executeUpdate(sql1);// 受影响的行数
            int count2 = stmt.executeUpdate(sql2);

            // 6. 处理结果
            System.out.println(count1 + "&" + count2);
            // 提交事务
            conn.commit();
        } catch (Exception throwables) {
            // 回滚事务
            conn.rollback();
            throwables.printStackTrace();
        }
        // 7. 释放资源
        stmt.close();
        conn.close();
    }
}

