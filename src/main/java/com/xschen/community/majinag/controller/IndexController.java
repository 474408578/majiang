package com.xschen.community.majinag.controller;

import com.xschen.community.majinag.dto.QuestionDTO;
import com.xschen.community.majinag.mapper.UserMapper;
import com.xschen.community.majinag.model.User;
import com.xschen.community.majinag.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Controller 将当前的类作为路由API的承载者
 */


@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
//        防止空指针异常
        if (cookies != null){
            for (Cookie cookie: cookies) {
                if (cookie.getName() == "token") {
                    String value = cookie.getValue();
                    User user = userMapper.findByToken(value);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<QuestionDTO> questionDTOS = questionService.list();
        model.addAttribute("questions", questionDTOS);
        return "index";
    }
}