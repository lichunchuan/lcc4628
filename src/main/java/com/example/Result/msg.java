package com.example.Result;

import java.io.Serializable;

public class msg<T> implements Serializable {
    private Boolean status;

    private String info;

    private T data;

    public msg() {

    }

    public msg(Boolean status, String info, T data) {
        this.status = status;
        this.info = info;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
