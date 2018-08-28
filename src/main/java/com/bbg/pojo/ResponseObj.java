package com.bbg.pojo;

/**
 * Created by H1N1 on 2018/8/24.
 */
public class ResponseObj {

    private String status;//状态码
    private String message;//信息
    private Object obj;//结果对象

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }


}
