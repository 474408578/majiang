package com.xschen.community.majinag.provider;

import com.alibaba.fastjson.JSON;
import com.xschen.community.majinag.dto.AccessTokenDTO;
import com.xschen.community.majinag.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Compoent 将当前的类初始化到Spring容器的上下文
 */

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str_resp = response.body().string();
            String access_token = str_resp.split("&")[0].split("=")[1];
            System.out.println(access_token);
            return access_token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public GithubUser getUser(String access_token){
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + access_token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String str_resp = response.body().string();
            GithubUser githubUser = JSON.parseObject(str_resp, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}