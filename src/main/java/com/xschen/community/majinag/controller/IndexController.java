package com.xschen.community.majinag.controller;

import com.xschen.community.majinag.dto.PaginationDTO;
import com.xschen.community.majinag.mapper.UserMapper;
import com.xschen.community.majinag.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
     * @param model
     * @param page 当前的页数
     * @param size 每一页内有多少条数据
     * @return
     */
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size) {
        // 将页码的参数传入到service的list方法中
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}