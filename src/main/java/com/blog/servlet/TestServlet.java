package com.blog.servlet;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.blog.utils.JsonReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author shibo
 */
public class TestServlet extends HttpServlet {
    public TestServlet (){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        /** 设置响应头允许ajax跨域访问 **/
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        Writer out = response.getWriter();
        String a = request.getParameter("user");
        JSONObject requestJson = JsonReader.receivePost(request);
        System.out.println(requestJson);

        JSONObject resultJson=new JSONObject() ;
        resultJson.put("user", "123");
        resultJson.put("message", "用户登录成功！");
        out.write(resultJson.toString());
        out.flush();


    }

}
