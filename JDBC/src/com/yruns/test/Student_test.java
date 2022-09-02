package com.yruns.test;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.yruns.pojo.Student;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Student_test
 */
public class Student_test {

    private static Connection conn = null;

    static {
        try {
            // 1.获取Connection
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/druid.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有
     * 1. select * from student
     * 2. 参数：无
     * 3. 结果List<Brand>
     */
    @Test
    public void testSelectAll() throws Exception {

        // 2.定义Sql
        String sql = "select * from student;";
        // 3.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // 4.设置参数

        // 5.执行sql
        ResultSet rs = pstmt.executeQuery();

        // 6.处理结果
        List<Student> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int age = rs.getInt(3);

            Student student = new Student(id, name, age);
            list.add(student);
        }

        System.out.println(list);

        // 7. 释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }
}
