package com.example.kotlinmvp.model;

import com.alibaba.fastjson.JSON;
import com.camming.mvp.http.XHttp;

import java.util.List;

/***统一处理服务端返回得数据*/
public class JsonResult<T> {

    private String reason;
    private T result;
    private int error_code;


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
