package com.example.Result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private int code;//状态码

    private String msg;//信息

    private Object data;//数据
}
