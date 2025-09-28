package com.lsk.springbootmall.dao;

import com.lsk.springbootmall.dto.UserRegisterRequest;
import com.lsk.springbootmall.model.User;

public interface UserDao {
    User getUserById(Integer userId);
    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserByEmail(String email);
}
