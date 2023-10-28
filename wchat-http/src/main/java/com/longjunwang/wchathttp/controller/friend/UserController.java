package com.longjunwang.wchathttp.controller.friend;

import com.longjunwang.wchatcommon.pojo.Response;
import com.longjunwang.wchatcommon.pojo.vo.UserInfoVo;
import com.longjunwang.wchathttp.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("search")
    public Response<UserInfoVo> searchOneByEmail(String email){
        log.info("email: {}",email);
        return userService.searchOneByEmail(email);
    }

    @GetMapping("get/{token}")
    public Response<UserInfoVo> getByToken(@PathVariable String token){
        log.info("token: {}",token);
        return userService.getByToken(token);
    }
}
