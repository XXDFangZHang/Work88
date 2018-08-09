package com.cjh.controller;
import com.alibaba.fastjson.JSON;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.lang.reflect.Method;
public abstract class BaseServlet extends HttpServlet{
    public   abstract  Class  getServletClass();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName=  req.getParameter("methodName");

        Method method=null;
        //执行方法的返回值
        Object result=null;
        if (methodName==null||"".equals(methodName)){
            result= execute(req,resp);  //统一返回到 主页面
        }else {
            try {
                method= getServletClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
                result= method.invoke(this,req,resp); //执行方法

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        toView(req,resp,result);
    }
    private void toView(HttpServletRequest req, HttpServletResponse resp, Object result) throws ServletException, IOException {
        if (result==null){

        }else {
            if (result instanceof String){
                String  viewName=result.toString()+".jsp";

                req.getRequestDispatcher(viewName).forward(req,resp);
            }else{

                String resultJson= (String) JSON.toJSONString(result);

                PrintWriter writer=resp.getWriter();
                writer.write(resultJson);
                writer.flush();
                writer.close();
            }
        }

    }

    private Object execute(HttpServletRequest req, HttpServletResponse resp) {
        return "main";
    }
}
