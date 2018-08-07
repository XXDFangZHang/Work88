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
        System.out.println("=====01:先进入");
        Method method=null;
        //执行方法的返回值
        Object result=null;
        if (methodName==null||"".equals(methodName)){
            result= execute(req,resp);  //统一返回到 主页面
        }else {
            try {
                method= getServletClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
                result= method.invoke(this,req,resp); //执行方法
                System.out.println("======>获取了 需要返回的页面"+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        toView(req,resp,result);
    }
    private void toView(HttpServletRequest req, HttpServletResponse resp, Object result) throws ServletException, IOException {
        if (result==null){
            System.out.println("=====没有返回值=====");
        }else {
            if (result instanceof String){
                String  viewName=result.toString()+".jsp";
                System.out.println("====>最终的跳转页面===》"+viewName);
                req.getRequestDispatcher(viewName).forward(req,resp);
            }else{
                System.out.println("====>json数据的处理===》");
                String resultJson= (String) JSON.toJSONString(result);
                System.out.println("json=====>"+resultJson);
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
