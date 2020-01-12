package com.xschen.community.majinag.mapper;

import com.xschen.community.majinag.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title, description, tag, creator, gmt_create, gmt_modified) values (#{title}, #{description}, #{tag}, #{creator}, #{gmtCreate}, #{gmtModified})")
    void create(Question question);
}
