package com.longjunwang.wchatcommon.pojo;

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

    public static Response<String> error(ResponseCode code) {
        Response<String>  response = new Response<> ();
        response.setCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }

    public static <T> Response<T> customer(ResponseCode code, String msg, T data){
        Response<T> response = new Response<>();
        response.setCode(code.getCode());
        response.setMessage(msg);
        response.setData(null);
        return response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
