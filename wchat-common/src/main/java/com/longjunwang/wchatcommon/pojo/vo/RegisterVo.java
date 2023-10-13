package com.longjunwang.wchatcommon.pojo.vo;

import lombok.Data;

@Data
public class RegisterVo {
    private String email;
    private String checkCode;
    private String passWord;
}
