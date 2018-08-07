package com.cjh.controller;

import javax.servlet.http.HttpServletRequest;
import com.cjh.util.ResultUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class UserServlet extends  BaseServlet{

    @Override
    public Class getServletClass() {
        System.out.println("=====02:UserServlet===》getServletClass");
        return UserServlet.class;
    }


    public ResultUtil login(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("====>UserServlet===>login");
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        ResultUtil util=new ResultUtil();

        if (userName.equals("admin")){
            util.resultSuccess(userName);
        }else{
            util.resultFail("用户名错误");
        }
        return util;
    }
}
