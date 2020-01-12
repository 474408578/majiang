package com.xschen.community.majinag.mapper;

import com.xschen.community.majinag.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id, name, token, avatar_url, gmt_create, gmt_modified) values (#{accountId}, #{name}, #{token}, #{avatarUrl}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);//不是类的时候使用@Param将token的值赋给SQL语句的token

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);
}
