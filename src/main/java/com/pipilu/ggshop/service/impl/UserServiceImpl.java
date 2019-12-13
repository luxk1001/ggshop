package com.pipilu.ggshop.service.impl;

import com.pipilu.ggshop.bean.User;
import com.pipilu.ggshop.dao.UserDao;
import com.pipilu.ggshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> selectUserAll() {
        return userDao.selectUserAll();
    }
}
