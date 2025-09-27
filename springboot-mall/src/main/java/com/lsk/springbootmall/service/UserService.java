package com.lsk.springbootmall.service;

import com.lsk.springbootmall.dto.UserRegisterRequest;
import com.lsk.springbootmall.model.User;

public interface UserService {
    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
}
