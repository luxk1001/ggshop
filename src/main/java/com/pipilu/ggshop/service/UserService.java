package com.pipilu.ggshop.service;

import com.pipilu.ggshop.bean.User;

import java.util.List;

public interface UserService {
    List<User> selectUserAll();

    void insertUser(User user);

    void updateUserPara1(String username);
}
