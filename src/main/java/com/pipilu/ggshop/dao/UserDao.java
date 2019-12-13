package com.pipilu.ggshop.dao;

import com.pipilu.ggshop.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> selectUserAll();
}
