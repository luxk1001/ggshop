package com.pipilu.ggshop.service.impl;

import com.pipilu.ggshop.bean.User;
import com.pipilu.ggshop.dao.UserDao;
import com.pipilu.ggshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> selectUserAll() {
        return userDao.selectUserAll();
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void updateUserPara1(String username) {
        userDao.updateUserPara1(username);
    }
}
