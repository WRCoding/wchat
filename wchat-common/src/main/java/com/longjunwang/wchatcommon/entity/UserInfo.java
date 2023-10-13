package com.longjunwang.wchatcommon.entity;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

@Data
public class UserInfo {
        private String id;
        @Alias( "login")
        private String userName;
        private String avatarUrl;
        private String phone;
        private String passWord;
        private String email;
        private String createTime;
        private String updateTime;
}
