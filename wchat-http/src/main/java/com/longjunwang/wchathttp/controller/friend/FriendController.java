package com.longjunwang.wchathttp.controller.friend;

import com.longjunwang.wchatcommon.pojo.Response;
import com.longjunwang.wchathttp.service.friend.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Resource
    private FriendService friendService;

    @GetMapping("/addFriend")
    public Response<String> addFriend(String userId, String friendId){
        log.info("userId: {}, friendId: {}", userId, friendId);
        return friendService.addFriend(userId, friendId);
    }
}
