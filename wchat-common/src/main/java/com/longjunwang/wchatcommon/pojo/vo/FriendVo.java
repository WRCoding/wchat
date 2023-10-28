package com.longjunwang.wchatcommon.pojo.vo;

import lombok.Data;

@Data
public class FriendVo {
    private String id;
    private String userName;
    private String email;
    private String phone;
    private String avatarUrl;
    private int isFriend;
}
