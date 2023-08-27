package com.longjunwang.wchathttp.service.oauth;

import cn.hutool.json.JSONUtil;
import com.longjunwang.wchatcommon.common.Constants;
import com.longjunwang.wchatcommon.entity.ouath.AccessTokenDTO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class OauthService {

    public String getAccessToken(String code) throws IOException {
        ResponseBody responseBody = sendAccessTokenRequest(code);
        if (Objects.nonNull(responseBody)){
            String str = responseBody.string();
            log.info("responseBody: {}", str);
            Map<String, String> bean = JSONUtil.toBean(str, Map.class);
            log.info("map: {}", bean);
            log.info("access_token: {}", bean.get("access_token"));
            getUserInfo(bean.get("access_token"));
        }
        return code;
    }

    public String getUserInfo(String token) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(Constants.USER_URL)
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        log.info(response.body().string());
        return "111";
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
