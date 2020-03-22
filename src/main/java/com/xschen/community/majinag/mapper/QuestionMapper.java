package com.xschen.community.majinag.mapper;

import com.xschen.community.majinag.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title, description, tag, creator, gmt_create, gmt_modified) values (#{title}, #{description}, #{tag}, #{creator}, #{gmtCreate}, #{gmtModified})")
    void create(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param("offset") Integer page, @Param("size") Integer size);

//    返回总的记录数
    @Select("select count(1) from question")
    Integer count();
}
