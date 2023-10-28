package com.longjunwang.wchatcommon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FriendRelation {
    private String userId;
    private String friendId;
    private String agree;
    private String createTime;
    private String updateTime;

    public FriendRelation(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }
}
