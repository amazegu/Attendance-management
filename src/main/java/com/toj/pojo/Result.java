package com.toj.pojo;

public class Result {
    private boolean success;
    private String msg;
    private Object data;

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result() {
    }

    public Result(boolean isOk, String message) {
        this.success = isOk;
        this.msg = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}


