package com.xschen.community.majinag.controller;

import com.xschen.community.majinag.dto.AccessTokenDTO;
import com.xschen.community.majinag.dto.GithubUser;
import com.xschen.community.majinag.mapper.UserMapper;
import com.xschen.community.majinag.model.User;
import com.xschen.community.majinag.provider.GithubProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;


    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;


    // https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/
    // 点击登录后，跳转至登录的URL页面进行github认证，认证成功后，会跳转至callback路由，此时会执行这个函数
    // 获取github返回的code和state值，并模拟http请求，
    // 调用github的access_token接口，拿到用户的access_token
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null){
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);

            response.addCookie(new Cookie("token", token));
            // 登录成功，写入session和cookie
            request.getSession().setAttribute("user", githubUser);
            return "redirect:/";
        } else {
            // 登录失败, 重新登录
            return "redirect:/";
        }
    }
}