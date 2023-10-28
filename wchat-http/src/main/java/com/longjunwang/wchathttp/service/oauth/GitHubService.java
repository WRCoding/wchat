package com.longjunwang.wchathttp.service.oauth;

import cn.hutool.json.JSONUtil;
import com.longjunwang.wchatcommon.common.Constants;
import com.longjunwang.wchatcommon.pojo.ouath.AccessTokenDTO;
import com.longjunwang.wchatcommon.entity.UserInfo;
import com.longjunwang.wchatcommon.pojo.vo.UserInfoVo;
import com.longjunwang.wchathttp.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class GitHubService {

    @Resource
    private UserService userService;

    public com.longjunwang.wchatcommon.pojo.Response<UserInfoVo> getAccessToken(String code) throws IOException {
        ResponseBody responseBody = sendAccessTokenRequest(code);
        com.longjunwang.wchatcommon.pojo.Response<UserInfoVo> userInfoVo = new com.longjunwang.wchatcommon.pojo.Response<>();
        if (Objects.nonNull(responseBody)){
            String str = responseBody.string();
            log.info("responseBody: {}", str);
            Map<String, String> bean = JSONUtil.toBean(str, Map.class);
            String accessToken = bean.get("access_token");
            log.info("map: {}", bean);
            log.info("access_token: {}", accessToken);
            userInfoVo = saveUserInfo(accessToken, code);
        }
        return userInfoVo;
    }

    private com.longjunwang.wchatcommon.pojo.Response<UserInfoVo> saveUserInfo(String accessToken, String code) throws IOException {
         return userService.loginByGitHub(getUserInfo(accessToken), code);
    }

    public UserInfo getUserInfo(String token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constants.USER_URL)
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return JSONUtil.toBean(response.body().string(), UserInfo.class);
    }

    private ResponseBody sendAccessTokenRequest(String code) throws IOException {
        OkHttpClient client = new OkHttpClient();
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO("6ec3c6a6c321273712c9", "8dfa19a142bd352680105f8b7e8f492c60e51fcb", code);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(JSONUtil.toJsonStr(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .addHeader("Accept", "application/json")
                .url(Constants.TOKEN_URL)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        return response.body();
    }

}
