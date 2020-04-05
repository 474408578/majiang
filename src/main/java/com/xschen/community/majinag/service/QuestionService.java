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
        Integer totalPage = ((totalCount % size) == 0) ? (totalCount / size) : (totalCount / size + 1);
        // page的两种特殊情况的处理


        if (page < 1) {
            page = 1;
        }

        if (page > totalPage && page > 1) {
            page = totalPage;
        }


        paginationDTO.setPagination(totalPage, page);

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

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        // 总的条数
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage && page > 1) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        // 从数据库中查出需要返回的条目
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
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
