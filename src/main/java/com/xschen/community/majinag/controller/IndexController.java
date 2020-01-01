package com.xschen.community.majinag.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Controller 将当前的类作为路由API的承载者
 */


@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
