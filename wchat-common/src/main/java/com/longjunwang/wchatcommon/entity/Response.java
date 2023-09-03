package com.longjunwang.wchatcommon.entity;

import com.longjunwang.wchatcommon.constant.ResponseCode;
import lombok.Data;

@Data
public class Response<T> {
    private String code;
    private String message;
    private T data;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }

    public static Response<?> success() {
        Response<?>  response = new Response<> ();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        return response;
    }

    public static Response<?> error(ResponseCode code) {
        Response<?>  response = new Response<> ();
        response.setCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }

    public static Response<String> customer(ResponseCode code, String errorMsg){
        Response<String> response = new Response<>();
        response.setCode(code.getCode());
        response.setMessage(code.getMessage());
        response.setMessage(errorMsg);
        return response;
    }
}
