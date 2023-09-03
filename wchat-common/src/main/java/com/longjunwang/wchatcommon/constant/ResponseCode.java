package com.longjunwang.wchatcommon.constant;

public enum ResponseCode {

    SUCCESS("200", "成功"),
    ERROR("500", "系统走神啦,请联系相关人员"),

    //业务码
    PARAM_ERROR("1001", "参数错误"),
    VERIFY_ERROR("1002", "校验失败"),
    ;

    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
