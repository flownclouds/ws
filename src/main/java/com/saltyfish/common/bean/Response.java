package com.saltyfish.common.bean;

import java.util.Map;

/**
 * Created by weck on 16/9/2.
 */
public class Response {
    private Integer code;
    private Map<String, Object> data;
    private String error;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
