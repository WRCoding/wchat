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

    public static Response<?> error() {
        Response<?>  response = new Response<> ();
        response.setCode(ResponseCode.ERROR.getCode());
        response.setMessage(ResponseCode.ERROR.getMessage());
        return response;
    }
}
