package com.lsk.springbootmall.service;

import com.lsk.springbootmall.dao.UserDao;
import com.lsk.springbootmall.dto.UserRegisterRequest;
import com.lsk.springbootmall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
