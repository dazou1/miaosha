package com.dazou.miaosha.dao;

import com.dazou.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 16:02 2019-04-08
 */

@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") int id);

    @Insert("insert into user(id, name)values(#{id}, #{name})")
    public int insert(User user);
}
