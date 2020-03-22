package com.xschen.community.majinag.service;

import com.xschen.community.majinag.dto.PaginationDTO;
import com.xschen.community.majinag.dto.QuestionDTO;
import com.xschen.community.majinag.mapper.QuestionMapper;
import com.xschen.community.majinag.mapper.UserMapper;
import com.xschen.community.majinag.model.Question;
import com.xschen.community.majinag.model.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 同时使用user和question进行组装
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据页数以及页大小返回一个paginationDTO数组
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        // 总的条数
        Integer totalCount = questionMapper.count();

        paginationDTO.setPagination(totalCount, page, size);

        // page的两种特殊情况的处理
        if (page < 1) {
            page = 1;
        }

        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }

        Integer offset = size * (page - 1);
        // 从数据库中查出需要返回的条目
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        // 将从数据库中查出的条目封装为questionDTO
        for (Question question: questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        // 设置paginationDTO
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
}
