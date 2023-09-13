package com.longjunwang.wchatcommon.entity.vo;

import lombok.Data;

public class LoginVo {
    private String key;
    private String passWord;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "key='" + key + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
