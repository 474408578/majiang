package com.xschen.community.majinag.controller;

import com.xschen.community.majinag.mapper.QuestionMapper;
import com.xschen.community.majinag.mapper.UserMapper;
import com.xschen.community.majinag.model.Question;
import com.xschen.community.majinag.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(
            @Param("title") String title,
            @Param("description") String description,
            @Param("tag") String tag,
            HttpServletRequest request,
            Model model){

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == ""){
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }

        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }


        Question question = new Question();


        User user =(User) request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        question.setCreator(user.getId());
        System.out.println(user.getId());
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
