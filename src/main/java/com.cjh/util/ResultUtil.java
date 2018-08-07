package com.cjh.util;

public class ResultUtil {

    private int status;   // 状态  1成功   0失败
    private String message;   // 错误信息
    private Object  data; // 返回的数据

    public ResultUtil resultSuccess(Object data){
        this.data=data;
        this.status=1;
        return  this;
    }
    public ResultUtil resultFail(String message){
        this.message=message;
        this.status=0;
        return  this;
    }

    public ResultUtil(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResultUtil() {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
