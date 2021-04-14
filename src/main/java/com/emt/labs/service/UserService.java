package com.emt.labs.service;

import com.emt.labs.model.User;
import com.emt.labs.model.dto.JwtResponseDto;
import com.emt.labs.model.dto.LoginDto;
import com.emt.labs.model.dto.RegisterDto;

import java.util.List;

public interface UserService {
    User findById(String username);

    List<User> findAll();

    JwtResponseDto signInUser(LoginDto loginDto);

    void signUpUser(RegisterDto registerDto);
}
