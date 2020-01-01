package com.xschen.community.majinag.controller;

import com.xschen.community.majinag.dto.AccessTokenDTO;
import com.xschen.community.majinag.dto.GithubUser;
import com.xschen.community.majinag.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;




    // 获取github返回的code和state值，并模拟http请求，
    // 调用github的access_token接口，拿到用户的access_token
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8888/callback");
        accessTokenDTO.setClient_id("96514d36d3befe4a5c2f");
        accessTokenDTO.setClient_secret("22486db2162e67a8be1fb54783fffa9ecba98e71");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());
        return "index";
    }
}
