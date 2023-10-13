package com.longjunwang.wchatcommon.pojo.vo;

import lombok.Data;

@Data
public class UserInfoVo {
    private String userName;
    private String email;
    private String phone;
    private String avatarUrl;

    private String ipAndPort;
}
