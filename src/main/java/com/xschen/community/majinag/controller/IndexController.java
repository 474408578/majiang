package com.xschen.community.majinag.controller;

import com.xschen.community.majinag.dto.PaginationDTO;
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

    /**
     * @param request
     * @param model
     * @param page 当前的页数
     * @param size 每一页内有多少条数据
     * @return
     */
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "8") Integer size) {
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
        // 将页码的参数传入到service的list方法中
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}