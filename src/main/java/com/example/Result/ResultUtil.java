package com.example.Result;

import com.example.Result.Result;

public class ResultUtil {
    //当正确时返回的值
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(0);
        result.setUserMsg("OK");
        result.setData(data);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    //当错误时返回的值
    public static Result error(int code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setUserMsg(msg);
        return result;
    }

}
