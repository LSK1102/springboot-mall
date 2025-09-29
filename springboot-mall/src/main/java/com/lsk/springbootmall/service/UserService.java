package com.lsk.springbootmall.service;

import com.lsk.springbootmall.dto.UserLoginRequest;
import com.lsk.springbootmall.dto.UserRegisterRequest;
import com.lsk.springbootmall.model.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
    User login(UserLoginRequest userLoginRequest);
}
