package com.yruns.response;

import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/resp5")
public class ResponseDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.读取文件
        FileInputStream fileInputStream = new FileInputStream("C:/Users/86185/Pictures/pict.jpg");
        // 2.获取response字节输出流
        ServletOutputStream outputStream = response.getOutputStream();
        // 3.完成流的copy
//        byte[] buff = new byte[1024];
//        int len = 0;
//        while ((len = fileInputStream.read(buff)) != -1) {
//            outputStream.write(buff, 0, len);
//        }
        // 使用工具类
        IOUtils.copy(fileInputStream, outputStream);
        fileInputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
