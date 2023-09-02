package com.longjunwang.wchatcommon.entity.ouath;

import cn.hutool.core.annotation.Alias;
import cn.hutool.core.annotation.AliasFor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInfo {
        private String id;
        @Alias( "login")
        private String userName;
        private String avatarUrl;
        private String phone;
        private String email;
        private String createTime;
        private String updateTime;
}
