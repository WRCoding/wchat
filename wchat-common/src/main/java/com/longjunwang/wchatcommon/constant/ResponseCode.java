package com.longjunwang.wchatcommon.constant;

public enum ResponseCode {

    SUCCESS("200", "成功"),

    ERROR("500", "系统走神啦,请联系相关人员");

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
